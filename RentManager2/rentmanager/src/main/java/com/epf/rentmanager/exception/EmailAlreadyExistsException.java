package com.epf.rentmanager.exception;

public class EmailAlreadyExistsException extends ServiceException {
    public EmailAlreadyExistsException() {
        super("This email address already exists in the database.");
    }
}
