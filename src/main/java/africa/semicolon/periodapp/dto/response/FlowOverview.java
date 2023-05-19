package africa.semicolon.periodapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlowOverview {

    private List<LocalDate> flowDates;

    private List<Ovulation> ovulation;

    private List<LocalDate> fertilePeriods;

    private List<LocalDate> safePeriod;
}
