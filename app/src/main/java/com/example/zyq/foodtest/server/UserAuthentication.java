package com.example.zyq.foodtest.server;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lambdell on 15/4/14.
 */
public class UserAuthentication {

    private String phoneNumber = "";

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public boolean isValidPhoneNumber(String phoneNumber) {
        Pattern pattern = Pattern.compile("^(\\+\\d{2})?1\\d{10}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }

    public boolean isAvailablePhoneNumber(String phoneNumber) {
        return true;
    }

    public void setPhoneNumber(String phoneNumber) {

        this.phoneNumber = phoneNumber;
    }


    public boolean login() {
        return false;
    }


    public boolean logout() {
        return false;
    }


    public boolean register() {
        return false;
    }

    public UserAuthentication() {
    }

    public UserAuthentication(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
    }
}
