package com.example.employeemanagement.entities;

import androidx.room.Entity;

@Entity
public class HourlySalariedEmployee extends Employee {

    private Double hourly_rate;
    private Double total_hours;

    public HourlySalariedEmployee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emo_designation, String emp_gender, Double hourly_rate, Double total_hours) {
        super(emp_name, emp_dob, emp_email, emp_phone, emo_designation, emp_gender);
        this.total_hours = total_hours;
        this.hourly_rate = hourly_rate;
    }


    public Double getHourly_rate() {
        return hourly_rate;
    }

    public void setHourly_rate(Double hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public Double getTotal_hours() {
        return total_hours;
    }

    public void setTotal_hours(Double total_hours) {
        this.total_hours = total_hours;
    }

    @Override
    public double getTotalSalary() {
        return total_hours*hourly_rate;
    }

    @Override
    public String toString() {
        return super.toString()+ "HourlySalariedEmployee{" +
                "hourly_rate=" + hourly_rate +
                ", total_hours=" + total_hours +
                '}';
    }
}
