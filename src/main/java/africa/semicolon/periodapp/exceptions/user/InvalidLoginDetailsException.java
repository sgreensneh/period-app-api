package africa.semicolon.periodapp.exceptions.user;

import africa.semicolon.periodapp.exceptions.PeriodCareException;
import org.springframework.http.HttpStatus;

public class InvalidLoginDetailsException extends PeriodCareException {

    public InvalidLoginDetailsException() {
        this("Invalid login details");
    }

    public InvalidLoginDetailsException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
