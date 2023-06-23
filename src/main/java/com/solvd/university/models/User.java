package com.solvd.university.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {

    private int userId;

    private String name;

    private String surname;

    private int personalId;

    public User(){}

    private String email;
    public User (int userId, String userName, String password, int studentID){

    }

    public User (int userId, String name, String surname, int personalId, String email) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.email = email;
    }


    public int getUserId() {
        return userId;
    }

    @XmlAttribute(name = "id")
    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    @XmlElement(name = "surname")
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPersonalId() {
        return personalId;
    }

    @XmlElement(name = "personalId")
    public void setPersonalId(int personalId) {
        this.personalId = personalId;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return  "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", personalId=" + personalId +
                ", email='" + email + '\'' +
                '}';
    }
}
