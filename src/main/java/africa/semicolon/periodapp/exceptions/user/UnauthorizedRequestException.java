package africa.semicolon.periodapp.exceptions.user;

import africa.semicolon.periodapp.exceptions.PeriodCareException;

public class UnauthorizedRequestException extends PeriodCareException {

    public UnauthorizedRequestException() {
        this("Unauthorized");
    }

    public UnauthorizedRequestException(String message) {
        super(message);
    }
}
