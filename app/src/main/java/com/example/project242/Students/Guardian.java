package com.example.project242.Students;

public class Guardian {
    private String guardianName;
    private String relationship;
    private String guardianPhoneNumber;
    private String guardianEmail;

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
}
