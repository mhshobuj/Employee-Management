package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.utils.ConstantUtils;
import com.google.android.material.textfield.TextInputEditText;

public class LoginActivity extends AppCompatActivity {

    private AuthPreference authPreference;
    private TextInputEditText emailTET, passTET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        emailTET = findViewById(R.id.adminEmailAddress);
        passTET = findViewById(R.id.adminPassword);

        authPreference = new AuthPreference(this);

        if (authPreference.isAdminLogedIn()){
            goDashboardActivity();
        }
    }

    public void adminLogin(View view) {

        String email = emailTET.getText().toString();
        String pass = passTET.getText().toString();

        if (email.equals(ConstantUtils.Admin.EMAIL_ADDRESS) &&
            pass.equals(ConstantUtils.Admin.PASSWORD)){
            authPreference.setLoginStatus(true);
            goDashboardActivity();
        }
        else {
            Toast.makeText(this, "Wrong Email or Password", Toast.LENGTH_LONG).show();
        }
    }
    private void goDashboardActivity(){
        finish();
        Intent intent = new Intent(LoginActivity.this, AdminDashboardActivity.class);
        startActivity(intent);
    }
}
