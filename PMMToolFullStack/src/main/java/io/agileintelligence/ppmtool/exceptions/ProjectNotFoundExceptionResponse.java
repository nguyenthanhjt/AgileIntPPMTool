package io.agileintelligence.ppmtool.exceptions;

public class ProjectNotFoundExceptionResponse {
    private String errorMessage;

    public ProjectNotFoundExceptionResponse(String message) {
        this.errorMessage = message;
    }

    public String getMessage() {
        return errorMessage;
    }

    public void setMessage(String message) {
        this.errorMessage = message;
    }
}
