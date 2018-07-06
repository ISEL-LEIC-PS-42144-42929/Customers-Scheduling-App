package com.ps.isel.customersscheduling;

public class IdTokenAndEmailContainer {

    private String idToken;
    private String email;

    private static IdTokenAndEmailContainer instance = new IdTokenAndEmailContainer();

    private IdTokenAndEmailContainer(){

    }

    public static IdTokenAndEmailContainer getInstance() {
        return instance;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getIdToken() {
        return idToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
