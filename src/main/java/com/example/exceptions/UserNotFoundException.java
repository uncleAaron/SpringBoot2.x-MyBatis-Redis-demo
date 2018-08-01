package com.example.exceptions;

public class UserNotFoundException extends RuntimeException {

    private int userID;

    public UserNotFoundException (int userID) {
        this.userID = userID;
    }

    public int getUserID() {
        return userID;
    }

    public String toString() {
        return "User(" + userID + ") Not Found";
    }

}
