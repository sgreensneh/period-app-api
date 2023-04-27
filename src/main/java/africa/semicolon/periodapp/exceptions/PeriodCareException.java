package africa.semicolon.periodapp.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class PeriodCareException extends RuntimeException {

    private final HttpStatus status;

    public PeriodCareException() {
        this("An error occurred");
    }

    public PeriodCareException(String message) {
        this(message, HttpStatus.BAD_REQUEST);
    }

    public PeriodCareException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
