package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.employeemanagement.adapters.BasedSalariedEmployeeAdapter;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.roomdb.EmployeeDB;
import com.example.employeemanagement.utils.ConstantUtils;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    private Spinner empTypeSP;
    private RecyclerView empRV;
    private BasedSalariedEmployeeAdapter employeeAdapter;

    private AuthPreference authPreference;

    private String[] empList = {
            ConstantUtils.EmployeeType.BASED_SALARIED,
            ConstantUtils.EmployeeType.HOURLU_SALARIED,
            ConstantUtils.EmployeeType.BASED_COMMISSION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        authPreference = new AuthPreference(this);

        setTitle("Employee List");
        empTypeSP = findViewById(R.id.empTypeSP);
        empRV = findViewById(R.id.empRV);
        List<BasedSalariedEmployee> bseList =
                EmployeeDB.getInstance(this)
                .getBasiedSalariedEmployeeDAO()
                .getAllBasedSalariedEmployee();
        employeeAdapter = new BasedSalariedEmployeeAdapter(this, bseList);

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, empList);
        empTypeSP.setAdapter(adapter);

        empTypeSP.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = empList[position];
                Toast.makeText(EmployeeListActivity.this, selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        //linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        empRV.setLayoutManager(linearLayoutManager);
        empRV.setAdapter(employeeAdapter);
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
                Intent intent = new Intent(EmployeeListActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.adminProfile:
                Intent intent1 = new Intent(EmployeeListActivity.this, AdminProfileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private List<BasedSalariedEmployee> generateEmployeeDummyList() {
        List<BasedSalariedEmployee> bselist = new ArrayList<>();
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("grt", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@ete.com", "01735391232", "tere", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("trt", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "yetye", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "tye@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("trt", "04/03/1996", "shobuj@gmail.com", "24563", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "grt", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "yety/03/1996", "yeye@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("trtiuo", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("shobuj", "04/03/1996", "shobuj@gmail.com", "01735391232", "manager", "male", 100000.0));
        bselist.add(new BasedSalariedEmployee("tet", "04/et/1996", "shobuj@et.com", "54854", "yrgdg", "female", 1000000.0));
        return bselist;
    }
}