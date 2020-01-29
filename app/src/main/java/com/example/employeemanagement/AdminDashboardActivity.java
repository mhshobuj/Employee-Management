package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class AdminDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        setTitle("Admin Dashboard");
    }

    public void registerNewEmployee(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,MainActivity.class));
    }

    public void viewEmployees(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,EmployeeListActivity.class));
    }
}
