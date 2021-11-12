package com.example.project242.Students;

import android.os.Parcel;
import android.os.Parcelable;

public class Student implements Parcelable {
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

    protected Student(Parcel in) {
        studentID = in.readInt();
        studentName = in.readString();
        studentDOB = in.readString();
        studentGender = in.readString();
    }

    public static final Creator<Student> CREATOR = new Creator<Student>() {
        @Override
        public Student createFromParcel(Parcel in) {
            return new Student(in);
        }

        @Override
        public Student[] newArray(int size) {
            return new Student[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(studentID);
        parcel.writeString(studentName);
        parcel.writeString(studentDOB);
        parcel.writeString(studentGender);
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
