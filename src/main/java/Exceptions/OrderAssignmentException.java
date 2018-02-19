package Exceptions;

// Jesli zamoloty beda mialy takie same numery, no to wtedy nie da sie przyporządkować

public class OrderAssignmentException extends Exception {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public OrderAssignmentException(String message)
    {
        this.message = message;
    }
}
