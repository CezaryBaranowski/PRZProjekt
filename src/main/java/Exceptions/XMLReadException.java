package Exceptions;

import java.io.IOException;

public class XMLReadException extends IOException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public XMLReadException(String message)
    {
        this.message = message;
    }

}