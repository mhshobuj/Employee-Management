package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.employeemanagement.prefs.AuthPreference;

public class AdminDashboardActivity extends AppCompatActivity {

    private AuthPreference authPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);
        authPreference = new AuthPreference(this);
        setTitle("Admin Dashboard");
    }

    public void registerNewEmployee(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,MainActivity.class));
    }

    public void viewEmployees(View view) {
        startActivity(new Intent(AdminDashboardActivity.this,EmployeeListActivity.class));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_manu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.adminSettings:
                Toast.makeText(this, "Coming Soon....", Toast.LENGTH_SHORT).show();
                break;

            case R.id.adminLogout:
                authPreference.setLoginStatus(false);
                finish();
                Intent intent = new Intent(AdminDashboardActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.adminProfile:
                Intent intent1 = new Intent(AdminDashboardActivity.this, AdminProfileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
