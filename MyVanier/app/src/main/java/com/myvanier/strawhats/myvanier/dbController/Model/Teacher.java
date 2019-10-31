package com.myvanier.strawhats.myvanier.dbController.Model;

public class Teacher {
    String name;
    String department;
    String phone;
    String email;
    String extension;
    String office;

    /**
     * Instantiates a default teacher object
     */
    public Teacher() {

    }

    /**
     * Instantiates a teacher object with specified parameters
     * @param name
     * @param department
     * @param phone
     * @param email
     * @param extension
     * @param office
     */
    public Teacher(String name, String department, String phone, String email, String extension, String office) {
        this.name = name;
        this.department = department;
        this.phone = phone;
        this.email = email;
        this.extension = extension;
        this.office = office;
    }

    /**
     * Gets the name of the teacher
     * @return the String of the department
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the department of the teacher
     * @return the String of the department
     */
    public String getDepartment(){return department;}

    /**
     * Gets the email of the teacher
     * @return the String of the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone of the teacher
     * @return the String of the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Gets the extension of the teacher
     * @return the String of the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * Sets the name of the teacher
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the department of the teacher
     * @param department
     */
    public void setDepartment(String department){
        this.department = department;
    }

    /**
     * Sets the email of the teacher
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Sets the phone of the teacher
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Sets the extension of the teacher
     * @param extension
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    /**
     * Gets the office of the teacher
     * @return the String of the office
     */
    public String getOffice() {
        return office;
    }

    /**
     * Sets the office of the teacher
     * @param office
     */
    public void setOffice(String office) {
        this.office = office;
    }
}
