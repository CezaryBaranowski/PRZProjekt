package Exceptions;

import java.io.IOException;

public class InvalidDatabaseDataValueException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public InvalidDatabaseDataValueException(String message)
    {
        this.message = message;
    }

}