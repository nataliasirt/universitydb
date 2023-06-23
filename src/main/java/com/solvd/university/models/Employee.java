package com.solvd.university.models;

public class Employee extends User{
    private int employeeId;
    private String position;
    private WorkedHours workedHours;

    public Employee (){}

    public Employee (int userId, String name, String surname, int personalId, String email, int employeeId, String position) {
        super(userId, name, surname, personalId, email);
        this.employeeId = employeeId;
        this.position = position;
    }

    public int getEmployeeId() {
        return this.employeeId;
    }

    public String getPosition() {
        return this.position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "employeeId=" + employeeId +
                ", position='" + position + '\'' +
                '}';
    }
}
