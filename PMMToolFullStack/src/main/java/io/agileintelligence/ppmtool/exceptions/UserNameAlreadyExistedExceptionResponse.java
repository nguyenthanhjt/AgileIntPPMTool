package io.agileintelligence.ppmtool.exceptions;

public class UserNameAlreadyExistedExceptionResponse {
    private String username;

    public UserNameAlreadyExistedExceptionResponse(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
