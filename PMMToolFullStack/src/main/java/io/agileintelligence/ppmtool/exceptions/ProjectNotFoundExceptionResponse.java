package io.agileintelligence.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {
    private String ERROR_MESSAGE;

    public ProjectNotFoundExceptionResponse(String message) {
        this.ERROR_MESSAGE = message;
    }

    public String getMessage() {
        return ERROR_MESSAGE;
    }

    public void setMessage(String message) {
        this.ERROR_MESSAGE = message;
    }
}
