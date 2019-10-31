package com.myvanier.strawhats.myvanier.dbController.Model;

public class Student {
    private int StudentID;
    private String fName;
    private String lName;

    public Student(String fname, String lname) {
        fName = fname;
        lName = lname;
    }

    public Student() {
        StudentID = 0;
        fName = "null";
        lName = "null";
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

}
