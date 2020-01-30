package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.utils.ConstantUtils;

public class AdminProfileActivity extends AppCompatActivity {
    private TextView adminInfoTV ;
    private EditText adminNameET, adminDesignationET, adminPhoneET;
    private Button saveInfoBT, adminMoreInfoAddBT;

    private AuthPreference authPreference;

    private final String NEW_LINE = "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

        authPreference = new AuthPreference(this);

        adminInfoTV = findViewById(R.id.adminInfo);
        adminNameET = findViewById(R.id.adminNameInput);
        adminDesignationET = findViewById(R.id.adminDesignationInput);
        adminPhoneET = findViewById(R.id.adminPhoneInput);

        saveInfoBT = findViewById(R.id.submitInfo);
        adminMoreInfoAddBT = findViewById(R.id.adminMoreInfoAdd);

        adminNameET.setVisibility(View.GONE);
        adminDesignationET.setVisibility(View.GONE);
        adminPhoneET.setVisibility(View.GONE);
        saveInfoBT.setVisibility(View.GONE);

    }

    public void addMoreInfo(View view) {
        adminInfoTV.setVisibility(View.GONE);
        adminNameET.setVisibility(View.VISIBLE);
        adminDesignationET.setVisibility(View.VISIBLE);
        adminPhoneET.setVisibility(View.VISIBLE);
        saveInfoBT.setVisibility(View.VISIBLE);
        adminMoreInfoAddBT.setVisibility(View.GONE);
    }

    public void save(View view) {
        adminInfoTV.setVisibility(View.VISIBLE);
        adminNameET.setVisibility(View.GONE);
        adminDesignationET.setVisibility(View.GONE);
        adminPhoneET.setVisibility(View.GONE);
        saveInfoBT.setVisibility(View.GONE);
        adminMoreInfoAddBT.setVisibility(View.GONE);

        String admin_gmail = adminInfoTV.getText().toString();
        String admin_name = adminNameET.getText().toString();
        String admin_designation = adminDesignationET.getText().toString();
        String admin_number = adminPhoneET.getText().toString();


        adminInfoTV.setText(admin_gmail+NEW_LINE+
                admin_name+NEW_LINE+
                admin_designation+NEW_LINE+
                admin_number);
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
                Intent intent = new Intent(AdminProfileActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.adminProfile:
                Intent intent1 = new Intent(AdminProfileActivity.this, AdminProfileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
