package africa.semicolon.periodapp.exceptions.user;

import africa.semicolon.periodapp.exceptions.PeriodCareException;
import org.springframework.http.HttpStatus;

public class UsernameAlreadyUsedException extends PeriodCareException {

    public UsernameAlreadyUsedException() {
        this("Username already used");
    }

    public UsernameAlreadyUsedException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}
