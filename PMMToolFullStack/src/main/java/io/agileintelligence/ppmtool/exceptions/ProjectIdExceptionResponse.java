package io.agileintelligence.ppmtool.exceptions;

public class ProjectIdExceptionResponse {
    private String error;

    public ProjectIdExceptionResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
