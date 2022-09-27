package com.example.gatekeeper;

import android.widget.EditText;
import android.widget.TextView;

public class UsersHelper {

    public String name, email, password;

    public UsersHelper() {
    }

    public UsersHelper(String name, String email, String password, String confirmpassword) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public UsersHelper(EditText regemail, EditText regpassword) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
