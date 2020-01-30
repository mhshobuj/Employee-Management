package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.employeemanagement.databinding.ActivityEmployeeDetailsBinding;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.roomdb.EmployeeDB;

import java.security.KeyStore;

public class EmployeeDetailsActivity extends AppCompatActivity {
    private ActivityEmployeeDetailsBinding binding;
    private long empId = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_details);
        empId = getIntent().getLongExtra("id", -1);
        if (empId > 0){
            BasedSalariedEmployee bse = EmployeeDB.getInstance(this)
                    .getBasiedSalariedEmployeeDAO()
                    .getBaseSalariedEmployeeID(empId);

            binding.empDetailsName.setText(bse.getEmp_name());
            binding.empDetailsDob.setText(bse.getEmp_dob());
            binding.empDetailsDesignation.setText(bse.getEmo_designation());
            binding.empDetailsEmail.setText(bse.getEmp_email());
            binding.empDetailsGender.setText(bse.getEmp_gender());
            binding.empDetailsPhone.setText(bse.getEmp_phone());
            binding.empDetailsBaseSalary.setText(String.valueOf(bse.getBase_salary()));

        }
    }
}
