package com.crud.apiApplication.model;

public class LoginResponseDTO {
    private User username;
    private String jwt;

    public LoginResponseDTO() {
        super();
    }

    public LoginResponseDTO(User username, String jwt) {
        this.username = username;
        this.jwt = jwt;
    }

    public User getUsername() {
        return this.username;
    }

    public void setUsername(User username) {
        this.username = username;
    }

    public String getJwt() {
        return this.jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
