package com.solvd.university.models;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "professor")
public class Professor extends User{

    private int professorId;

    private String degree;

    private WorkedHours workedHours;

    public Professor(){}


    public Professor(int userId, String name, String surname, int personalId, String email, int professorId, String degree) {
        super(userId, name, surname, personalId, email);
        this.professorId = professorId;
        this.degree = degree;
    }

    public int getProfessorId() {
        return this.professorId;
    }

    @XmlAttribute(name = "professorId")
    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public String getDegree() {
        return this.degree;
    }

    @XmlElement(name = "degree")
    public void setDegree(String degree) {
        this.degree = degree;
    }


    public WorkedHours getWorkedHours() {
        return workedHours;
    }

    public void setWorkedHours(WorkedHours workedHours) {
        this.workedHours = workedHours;
    }

    @Override
    public String toString() {
        return  "Professor{" +
                "professorId=" + professorId + " " +
                super.toString() +
                ", degree='" + degree + '\'' +
                ", workedHours=" + workedHours +
                '}';
    }
}
