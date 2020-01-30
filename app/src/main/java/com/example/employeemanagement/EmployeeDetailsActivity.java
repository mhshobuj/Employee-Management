package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.employeemanagement.databinding.ActivityEmployeeDetailsBinding;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.roomdb.EmployeeDB;

import java.security.KeyStore;

public class EmployeeDetailsActivity extends AppCompatActivity {
    private ActivityEmployeeDetailsBinding binding;
    private long empId = 0;

    private AuthPreference authPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_details);

        authPreference = new AuthPreference(this);

        empId = getIntent().getLongExtra("id", -1);
        if (empId > 0) {
            final BasedSalariedEmployee bse = EmployeeDB.getInstance(this)
                    .getBasiedSalariedEmployeeDAO()
                    .getBaseSalariedEmployeeID(empId);

            binding.empDetailsName.setText(bse.getEmp_name());
            binding.empDetailsDob.setText(bse.getEmp_dob());
            binding.empDetailsDesignation.setText(bse.getEmo_designation());
            binding.empDetailsEmail.setText(bse.getEmp_email());
            binding.empDetailsGender.setText(bse.getEmp_gender());
            binding.empDetailsPhone.setText(bse.getEmp_phone());
            binding.empDetailsBaseSalary.setText(String.valueOf(bse.getBase_salary()));

            //for dail up option
            /*binding.callImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri phoneUri = Uri.parse("tel:"+bse.getEmp_phone());
                    Intent intent = new Intent(Intent.ACTION_DIAL, phoneUri);
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(EmployeeDetailsActivity.this, "No Apps Founded", Toast.LENGTH_SHORT).show();
                    }
                }
            });*/
            //for direct call
            binding.callImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri phoneUri = Uri.parse("tel:" + bse.getEmp_phone());
                    Intent intent = new Intent(Intent.ACTION_CALL, phoneUri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        if (isCallPhonePermissionGranted()) {
                            if (checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            startActivity(intent);
                        }
                    }
                    else {
                        Toast.makeText(EmployeeDetailsActivity.this, "No Apps Founded", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            binding.smsImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri smsUri = Uri.parse("smsto:"+bse.getEmp_phone());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, smsUri);
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(EmployeeDetailsActivity.this, "No Apps Founded", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            binding.emailImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Uri emailUri = Uri.parse("mailto:"+bse.getEmp_email());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, emailUri);
                    if (intent.resolveActivity(getPackageManager()) != null){
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(EmployeeDetailsActivity.this, "No Apps Founded", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
    private boolean isCallPhonePermissionGranted(){
        String[] permissions = {Manifest.permission.CALL_PHONE};
        if (checkSelfPermission(Manifest.permission.CALL_PHONE) !=
                PackageManager.PERMISSION_GRANTED){
            requestPermissions(permissions, 1020);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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
                Intent intent = new Intent(EmployeeDetailsActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.adminProfile:
                Intent intent1 = new Intent(EmployeeDetailsActivity.this, AdminProfileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
