package africa.semicolon.periodapp.exceptions;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Data
@Builder
public class PeriodCareExceptionResponse {

    private Map<String, String> data;

    private String message;

    private HttpStatus status;

}
