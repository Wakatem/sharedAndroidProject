package com.example.project242.Students;

public class Student {
    private int studentID;
    private String studentName;
    private String studentDOB;
    private String studentGender;
    private Guardian guardian;

    public Student() {
        this.studentID = -1;
        this.studentName = "Null";
        this.studentDOB = "Null";
        this.studentGender = "Null";
        this.guardian = new Guardian();
    }

    public Student(int studentID, String studentName, String studentDOB, String studentGender, Guardian guardian) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentDOB = studentDOB;
        this.studentGender = studentGender;
        this.guardian = guardian;
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

    public Guardian getGuardian() {
        return this.guardian;
    }

    public void setGuardian(Guardian guardian) {
        this.guardian = guardian;
    }
}
