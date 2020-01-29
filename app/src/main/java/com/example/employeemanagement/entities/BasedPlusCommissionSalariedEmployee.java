package com.example.employeemanagement.entities;

import androidx.room.Entity;

@Entity
public class BasedPlusCommissionSalariedEmployee extends BasedSalariedEmployee {

    private Double commission_rate;
    private Double gross_sell;

    public BasedPlusCommissionSalariedEmployee(String emp_name, String emp_dob, String emp_email, String emp_phone, String emo_designation, String emp_gender, Double base_salary, Double commission_rate, Double gross_sell) {
        super(emp_name, emp_dob, emp_email, emp_phone, emo_designation, emp_gender, base_salary);
        this.commission_rate = commission_rate;
        this.gross_sell = gross_sell;
    }

    public Double getCommission_rate() {
        return commission_rate;
    }

    public void setCommission_rate(Double commission_rate) {
        this.commission_rate = commission_rate;
    }

    public Double getGross_sell() {
        return gross_sell;
    }

    public void setGross_sell(Double gross_sell) {
        this.gross_sell = gross_sell;
    }

    @Override
    public double getTotalSalary() {
        return getBase_salary()+(gross_sell*commission_rate);
    }

    @Override
    public String toString() {
        return super.toString()+"BasedPlusCommissionSalariedEmployee{" +
                "commission_rate=" + commission_rate +
                ", gross_sell=" + gross_sell +
                '}';
    }
}
