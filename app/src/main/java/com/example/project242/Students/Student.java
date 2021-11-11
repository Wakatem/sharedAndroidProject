package com.example.project242.Students;

public class Student {
    private int studentID;
    private String studentName;
    private String studentDOB;
    private String studentGender;

    public Student() {
        this.studentID = -1;
        this.studentName = "Null";
        this.studentDOB = "Null";
        this.studentGender = "Null";
    }

    public Student(int studentID, String studentName, String studentDOB, String studentGender) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentDOB = studentDOB;
        this.studentGender = studentGender;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentDOB() {
        return studentDOB;
    }

    public void setStudentDOB(String studentDOB) {
        this.studentDOB = studentDOB;
    }

    public String getStudentGender() {
        return studentGender;
    }

    public void setStudentGender(String studentGender) {
        this.studentGender = studentGender;
    }
}
