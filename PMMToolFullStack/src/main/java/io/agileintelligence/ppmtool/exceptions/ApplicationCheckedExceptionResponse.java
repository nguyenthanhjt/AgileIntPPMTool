package io.agileintelligence.ppmtool.exceptions;

public class ApplicationCheckedExceptionResponse {
    private String message;

    public ApplicationCheckedExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
