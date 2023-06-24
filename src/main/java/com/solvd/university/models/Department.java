package com.solvd.university.models;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "department")
public class Department {
    private int departmentId;
    private String area;
    private Professor head;
    @XmlElementWrapper(name = "professors")
    @XmlElement(name = "professor")
    private List<Professor> professors;

    public Department() {
    }

    public Department(int departmentId, String area, Professor head) {
        this.departmentId = departmentId;
        this.area = area;
        this.head = head;
    }


    public int getDepartmentId() {
        return departmentId;
    }

    @XmlAttribute(name = "departmentId")
    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public String getArea() {
        return area;
    }

    @XmlElement(name = "area")
    public void setArea(String area) {
        this.area = area;
    }

    public Professor getHead() {
        return head;
    }

    @XmlElement(name = "head")
    public void setHead(Professor head) {
        this.head = head;
    }

    public List<Professor> getProfessors() {
        return professors;
    }


    public void setProfessors(ArrayList<Professor> professors) {
        this.professors = professors;
    }
    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", area='" + area + '\'' +
                ", head=" + head +
                ", professors=" + professors +
                '}';
    }
}

