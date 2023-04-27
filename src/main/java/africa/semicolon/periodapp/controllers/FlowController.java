package africa.semicolon.periodapp.controllers;

import africa.semicolon.periodapp.dto.response.FlowOverview;
import africa.semicolon.periodapp.services.FlowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/flow")
@AllArgsConstructor
public class FlowController {

    private final FlowService flowService;

    @GetMapping("")
    @Operation(summary = "Get future menstrual overview")
    public ResponseEntity<List<FlowOverview>> getOverview(
            @RequestParam
            @Parameter(name = "length", description = "How many cycles overview")
            Optional<Integer> length) {
        return ResponseEntity.ok(flowService.getOverviews(length.orElse(12)));
    }
}
