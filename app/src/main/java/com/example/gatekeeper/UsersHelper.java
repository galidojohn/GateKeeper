package com.example.gatekeeper;

public class UsersHelper {
    public String name;
    public String email;
    public String password;
    public UsersHelper(){

    }

    public UsersHelper(String name, String email,String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}