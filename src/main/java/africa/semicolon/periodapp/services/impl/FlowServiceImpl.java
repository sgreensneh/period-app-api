package africa.semicolon.periodapp.services.impl;

import africa.semicolon.periodapp.data.models.Flow;
import africa.semicolon.periodapp.dto.response.Chance;
import africa.semicolon.periodapp.dto.response.FlowOverview;
import africa.semicolon.periodapp.dto.response.Ovulation;
import africa.semicolon.periodapp.services.FlowService;
import africa.semicolon.periodapp.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class FlowServiceImpl implements FlowService {

    private final UserService userService;

    @Override
    public List<FlowOverview> getOverviews(Integer len) {
        int length = len < 1 ? 1 : len > 36 ? 36 : len;

        Flow firstFlow = userService.getAuthenticatedUser().getFlow();
        LocalDate startDate = firstFlow.getLastFlow();
        Integer cycleCount = firstFlow.getCycleLength();

        List<FlowOverview> flows = new ArrayList<>();
        LocalDate currentDate = startDate;

        int i = 0;
        while (i < length) {
            LocalDate nextDate = currentDate.plusDays(cycleCount);

            if (nextDate.isAfter(LocalDate.now())) {
                FlowOverview flowOverview = getFlowOverview(nextDate);
                flows.add(flowOverview);
                i++;
            }
            currentDate = nextDate;
        }
        return flows;
    }

    private static FlowOverview getFlowOverview(LocalDate nextDate) {
        LocalDate possibleOvulationDate = nextDate.minusDays(14);

        List<Ovulation> ovulations = new ArrayList<>();
        for (int i = -2; i <= 2; i++) {
            Ovulation ovulation = Ovulation.builder()
                    .date(possibleOvulationDate.plusDays(i))
                    .chance(generateChance(i))
                    .build();
            ovulations.add(ovulation);
        }

        List<LocalDate> fertileDates = new ArrayList<>();
        for (int i = -7; i <= 2; i++) {
            fertileDates.add(possibleOvulationDate.plusDays(i));
        }

        List<LocalDate> freePeriod = new ArrayList<>();
        for (int i = -11; i <= -1; i++) {
            freePeriod.add(nextDate.plusDays(i));
        }

        return FlowOverview.builder()
                .flowDate(nextDate)
                .fertilePeriods(fertileDates)
                .safePeriod(freePeriod)
                .ovulation(ovulations)
                .build();
    }

    private static Chance generateChance(int i) {
        return switch (Math.abs(i)) {
            case 0 -> Chance.HIGH;
            case 1 -> Chance.MEDIUM;
            default -> Chance.LOW;
        };
    }
}
