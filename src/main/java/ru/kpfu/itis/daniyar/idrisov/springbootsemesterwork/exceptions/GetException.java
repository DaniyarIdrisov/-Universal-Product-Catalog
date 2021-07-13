package ru.kpfu.itis.daniyar.idrisov.springbootsemesterwork.exceptions;

public class GetException extends RuntimeException{

    public GetException() {
    }

    public GetException(String message) {
        super(message);
    }

    public GetException(String message, Throwable cause) {
        super(message, cause);
    }

    public GetException(Throwable cause) {
        super(cause);
    }

}
