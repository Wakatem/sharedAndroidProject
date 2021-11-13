package com.example.project242.Students;

import android.os.Parcel;
import android.os.Parcelable;

public class Guardian implements Parcelable {
    private String guardianName;
    private String relationship;
    private String guardianPhoneNumber;
    private String guardianEmail;
    private String guardianAccountNumber;

    public Guardian() {
        this.guardianName = "Null";
        this.relationship = "Null";
        this.guardianPhoneNumber = "Null";
        this.guardianEmail = "Null";
    }

    public Guardian(String guardianName, String relationship, String guardianPhoneNumber, String guardianEmail) {
        this.guardianName = guardianName;
        this.relationship = relationship;
        this.guardianPhoneNumber = guardianPhoneNumber;
        this.guardianEmail = guardianEmail;
    }

    protected Guardian(Parcel in) {
        guardianName = in.readString();
        relationship = in.readString();
        guardianPhoneNumber = in.readString();
        guardianEmail = in.readString();
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(guardianName);
        parcel.writeString(relationship);
        parcel.writeString(guardianPhoneNumber);
        parcel.writeString(guardianEmail);
    }

    public static final Creator<Guardian> CREATOR = new Creator<Guardian>() {
        @Override
        public Guardian createFromParcel(Parcel in) {
            return new Guardian(in);
        }

        @Override
        public Guardian[] newArray(int size) {
            return new Guardian[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getGuardianPhoneNumber() {
        return guardianPhoneNumber;
    }

    public void setGuardianPhoneNumber(String guardianPhoneNumber) {
        this.guardianPhoneNumber = guardianPhoneNumber;
    }

    public String getGuardianEmail() {
        return guardianEmail;
    }

    public void setGuardianEmail(String guardianEmail) {
        this.guardianEmail = guardianEmail;
    }

    public String getGuardianAccountNumber() {
        return this.guardianAccountNumber;
    }

    public void setGuardianAccountNumber(String guardianAccountNumber) {
        this.guardianAccountNumber = guardianAccountNumber;
    }
}
