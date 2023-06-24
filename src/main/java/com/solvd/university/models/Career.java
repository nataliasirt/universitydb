package com.solvd.university.models;

import java.util.List;

public class Career {


        private int careerId;

        private String title;

        private int duration;

        private double cost;

        private List<Subject> subjects;

        private List<Student> students;

        public Career() {
        }

        public Career(int careerId, String title, int duration, double cost) {
            this.careerId = careerId;
            this.title = title;
            this.duration = duration;
            this.cost = cost;
        }

        public int getCareerId() {
            return careerId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public List<Subject> getSubjects() {
            return subjects;
        }

        public void setSubjects(List<Subject> subjects) {
            this.subjects = subjects;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        @Override
        public String toString() {
            return "Career{" +
                    "careerId=" + careerId +
                    ", title='" + title + '\'' +
                    ", duration=" + duration +
                    ", cost=" + cost +
                    ", subjects=" + subjects +
                    ", students=" + students +
                    '}';
        }
    }


