package com.epf.rentmanager.exception;

public class ClientNotExistsException extends ServiceException {
    public ClientNotExistsException() {
        super("This client does not exist in the database.");
    }
}
