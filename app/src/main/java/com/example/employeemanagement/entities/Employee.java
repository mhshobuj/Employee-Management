package com.example.employeemanagement.entities;

import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public abstract class Employee {

    @PrimaryKey(autoGenerate = true)
    private long empID;
    @ColumnInfo(name = "name")
    private String emp_name;
    @ColumnInfo(name = "date_of_birth")
    private String emp_dob;
    @ColumnInfo(name = "email")
    private String emp_email;
    @ColumnInfo(name = "number")
    private String emp_phone;
    @ColumnInfo(name = "designation")
    private String emo_designation;
    @ColumnInfo(name = "gender")
    private String emp_gender;

    public abstract double getTotalSalary();

    public Employee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emo_designation, String emp_gender) {
        this.emp_name = emp_name;
        this.emp_dob = emp_dob;
        this.emp_email = emp_email;
        this.emp_phone = emp_phone;
        this.emo_designation = emo_designation;
        this.emp_gender = emp_gender;
    }

    public String getEmp_name() { return emp_name; }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_dob() {
        return emp_dob;
    }

    public void setEmp_dob(String emp_dob) {
        this.emp_dob = emp_dob;
    }

    public String getEmp_email() {
        return emp_email;
    }

    public void setEmp_email(String emp_email) {
        this.emp_email = emp_email;
    }

    public String getEmp_phone() {
        return emp_phone;
    }

    public void setEmp_phone(String emp_phone) {
        this.emp_phone = emp_phone;
    }

    public String getEmo_designation() {
        return emo_designation;
    }

    public void setEmo_designation(String emo_designation) {
        this.emo_designation = emo_designation;
    }

    public String getEmp_gender() {
        return emp_gender;
    }

    public void setEmp_gender(String emp_gender) {
        this.emp_gender = emp_gender;
    }

    public long getEmpID() {
        return empID;
    }

    public void setEmpID(long empID) {
        this.empID = empID;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "emp_name='" + emp_name + '\'' +
                ", emp_dob='" + emp_dob + '\'' +
                ", emp_email='" + emp_email + '\'' +
                ", emp_phone='" + emp_phone + '\'' +
                ", emo_designation='" + emo_designation + '\'' +
                ", emp_gender='" + emp_gender + '\'' +
                '}';
    }
}
