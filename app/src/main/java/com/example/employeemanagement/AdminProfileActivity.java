package com.example.employeemanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.employeemanagement.utils.ConstantUtils;

public class AdminProfileActivity extends AppCompatActivity {
    private TextView adminInfoTV ;
    private EditText adminNameET, adminDesignationET, adminPhoneET;
    private Button saveInfoBT, adminMoreInfoAddBT;

    private final String NEW_LINE = "\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);

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
}
