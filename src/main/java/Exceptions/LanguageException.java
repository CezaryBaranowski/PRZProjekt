package Exceptions;

import java.io.IOException;

public class LanguageException extends IOException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public LanguageException(String message)
    {
        this.message = message;
    }

}