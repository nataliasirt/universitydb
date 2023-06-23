package com.solvd.university.models;


public class WorkedHours {
        private int hoursId;

        private int amount;

        private String month;

        private int professorId;

        private int employeeId;


        public WorkedHours(int hoursId, int amount, String month) {
            this.hoursId = hoursId;
            this.amount = amount;
            this.month = month;
        }

        public int getHoursId() {
            return hoursId;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getMonth() {
            return month;
        }

        public void setMonth(String month) {
            this.month = month;
        }


        public int getProfessorId() {
            return professorId;
        }

        public void setProfessorId(int professorId) {
            this.professorId = professorId;
        }

        public int getEmployeeId() {
            return employeeId;
        }

        public void setEmployeeId(int employeeId) {
            this.employeeId = employeeId;
        }

        @Override
        public String toString() {
            return "WorkedHours{" +
                    "hoursId=" + hoursId +
                    ", amount=" + amount +
                    ", month='" + month + '\'' +
                    '}';
        }

    }

