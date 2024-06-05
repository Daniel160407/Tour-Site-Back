package com.toursiteback.service.exception;

public class UserNotAccessibleException extends RuntimeException {
    public UserNotAccessibleException() {
        super("This was not created or was deleted by admin");
    }
}
