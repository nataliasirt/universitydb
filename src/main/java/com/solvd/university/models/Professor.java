package com.solvd.university.models;

public class Professor  extends User {
    private int professorID;
    private String firstName;
    private String lastName;
    private String degree;

    public Professor(){}

    public Professor(int userID, String userName, String password, int studentID, int professorID, String firstName, String lastName, String degree) {
        super(userID, userName, password, studentID);
        this.professorID = professorID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.degree = degree;
    }

    public int getProfessorID() {
        return professorID;
    }

    public void setProfessorID(int professorID) {
        this.professorID = professorID;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "professorID=" + professorID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", degree='" + degree + '\'' +
                '}';
    }
}
