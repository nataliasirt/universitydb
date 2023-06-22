package com.solvd.university.models;

public class User {
    private int userID;
    private String userName;
    private int studentID;
    public User(){}

    public User(int userID, String userName, String password, int studentID) {
        this.userID = userID;
        this.userName = userName;
        this.studentID = studentID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userName='" + userName + '\'' +
                ", studentID=" + studentID +
                '}';
    }
}


