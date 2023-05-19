package africa.semicolon.periodapp.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignupRequestDto {

    @NotBlank(message = "Full name is required")
    private String fullName;

    @NotBlank(message = "Username is required")
    private String username;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Cycle length is required")
    private Integer cycleLength;

    @NotNull(message = "Flow length is required")
    private Integer flowLength;

    @NotNull(message = "Please provide your first flow date")
    @PastOrPresent(message = "Date should be in the past or today")
    private LocalDate firstFlow;

}
