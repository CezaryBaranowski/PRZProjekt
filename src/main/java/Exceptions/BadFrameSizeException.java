package Exceptions;

// Jesli zamoloty beda mialy takie same numery, no to wtedy nie da sie przyporządkować

public class BadFrameSizeException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public BadFrameSizeException(String message)
    {
        this.message = message;
    }
}
