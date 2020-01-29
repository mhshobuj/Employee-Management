package com.example.employeemanagement.entities;

import androidx.room.Entity;

@Entity(tableName = "tbl_based_salaried_emp")
public class BasedSalariedEmployee extends Employee {

    private Double base_salary;

    public BasedSalariedEmployee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emo_designation, String emp_gender, Double base_salary) {
        super(emp_name, emp_dob, emp_email, emp_phone, emo_designation, emp_gender);
        this.base_salary = base_salary;
    }

    public Double getBase_salary() {
        return base_salary;
    }

    public void setBase_salary(Double base_salary) {
        this.base_salary = base_salary;
    }

    @Override
    public double getTotalSalary() {
        return base_salary;
    }

    @Override
    public String toString() {
        return super.toString()+"BasedSalariedEmployee{" +
                "base_salary=" + base_salary +
                '}';
    }
}
