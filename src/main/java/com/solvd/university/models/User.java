package com.solvd.university.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    @JsonProperty
    private int userId;
    @JsonProperty
    private String name;
    @JsonProperty
    private String surname;
    @JsonProperty
    private int personalId;
    @JsonProperty
    private String email;
    public User (){

    }

    public User (int userId, String name, String surname, int personalId, String email) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.personalId = personalId;
        this.email = email;
    }

    public User (String name, String surname, int personalId, String email) {
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
