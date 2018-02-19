package Exceptions;

import java.io.IOException;

public class ReadPropertiesException extends IOException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

public ReadPropertiesException(String message)
    {
        this.message = message;
    }

}
