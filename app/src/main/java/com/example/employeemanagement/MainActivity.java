package com.example.employeemanagement;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.prefs.AuthPreference;
import com.example.employeemanagement.roomdb.EmployeeDB;
import com.example.employeemanagement.utils.ConstantUtils;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    private EditText nameET,dobET,phoneET,emailET,baseSalaryET,
            hourlySalaryET,totalHoursET,commissionRateET,grossRateET;

    private RadioGroup genderRG, empTypeRG;
    private LinearLayout empAllTypeLL;
    private String gender = "Male";
    private String emp_type = ConstantUtils.EmployeeType.BASED_SALARIED;
    private String dob = "";
    private AuthPreference authPreference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        authPreference = new AuthPreference(this);

        nameET = findViewById(R.id.empNameInput);
        dobET = findViewById(R.id.empDobInput);
        phoneET = findViewById(R.id.empPhoneInput);
        emailET = findViewById(R.id.empEmailInput);
        baseSalaryET = findViewById(R.id.empBasedSalaryInput);
        hourlySalaryET = findViewById(R.id.empHourlyRateInput);
        totalHoursET = findViewById(R.id.empTotalHoursInput);
        commissionRateET = findViewById(R.id.empCommissionRateInput);
        grossRateET = findViewById(R.id.empGrossSaleInput);

        genderRG = findViewById(R.id.genderRG);
        empTypeRG = findViewById(R.id.empTypeRG);

        empAllTypeLL = findViewById(R.id.empTypeLL);

        genderRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                gender = rb.getText().toString();
                Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        empTypeRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = findViewById(checkedId);
                emp_type = rb.getText().toString();
                activateDeactivateView(checkedId);
                Toast.makeText(MainActivity.this, rb.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
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
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
                break;

            case R.id.adminProfile:
                Intent intent1 = new Intent(MainActivity.this, AdminProfileActivity.class);
                startActivity(intent1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void activateDeactivateView(int checkedId) {

        //fadeIn Animation
        Animation anifadeIn = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fade_in);
        empAllTypeLL.startAnimation(anifadeIn);

        switch (checkedId){
            case R.id.rbTypeBaseSalariedEmp:
                baseSalaryET.setVisibility(View.VISIBLE);
                hourlySalaryET.setVisibility(View.GONE);
                totalHoursET.setVisibility(View.GONE);
                commissionRateET.setVisibility(View.GONE);
                grossRateET.setVisibility(View.GONE);
            break;

            case R.id.rbTypeHourlySalariedEmp:
                baseSalaryET.setVisibility(View.GONE);
                hourlySalaryET.setVisibility(View.VISIBLE);
                totalHoursET.setVisibility(View.VISIBLE);
                commissionRateET.setVisibility(View.GONE);
                grossRateET.setVisibility(View.GONE);
                break;

            case R.id.rbTypeBasePlusCommissionSalariedEmp:
                baseSalaryET.setVisibility(View.VISIBLE);
                hourlySalaryET.setVisibility(View.GONE);
                totalHoursET.setVisibility(View.GONE);
                commissionRateET.setVisibility(View.VISIBLE);
                grossRateET.setVisibility(View.VISIBLE);
                break;
        }

    }

    public void registerNewEmployee(View view) {
        String name = nameET.getText().toString();
        String email = emailET.getText().toString();
        String mobile = phoneET.getText().toString();

        switch (emp_type){
            case ConstantUtils.EmployeeType.BASED_SALARIED:
                String baseSalary = baseSalaryET.getText().toString();

                BasedSalariedEmployee bse = new BasedSalariedEmployee(name,dob,email,
                                mobile,emp_type,gender,
                                Double.parseDouble(baseSalary));

                final long empID = EmployeeDB.getInstance(this)
                        .getBasiedSalariedEmployeeDAO()
                        .insertBasiedSalariedEmployee(bse);

                if (empID > 0){
                    Toast.makeText(this, "saved successfully", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(MainActivity.this, EmployeeListActivity.class));
                }

                Log.e("Employee", "registerNewEmployee: "+bse);
                break;

            case ConstantUtils.EmployeeType.HOURLU_SALARIED:
                /*String totalHour = totalHourET.getText().toString();
                String hourlyRate = hourlyRateET.getText().toString();

                HourlySalariedEmployee hse = new HourlySalariedEmployee();

                hse.setEmp_name(name);
                hse.setEmp_dob(dob);
                hse.setEmp_email(email);
                hse.setEmp_phone(mobile);
                hse.setEmp_gender(gender);
                hse.setEmp_designation(emp_type);
                hse.setHourly_rate(Double.parseDouble(hourlyRate));
                hse.setTotal_hour(Double.parseDouble(totalHour));
                Log.e("Employee", "registerNewEmployee: "+hse);*/

                break;

            case ConstantUtils.EmployeeType.BASED_COMMISSION:
                String baseSalary1 = baseSalaryET.getText().toString();
                String commissionRate = commissionRateET.getText().toString();
                String grossSale = grossRateET.getText().toString();

                /*BasePlusCommissionedEmployee bpce = new BasePlusCommissionedEmployee();

                bpce.setEmp_name(name);
                bpce.setEmp_dob(dob);
                bpce.setEmp_email(email);
                bpce.setEmp_phone(mobile);
                bpce.setEmp_gender(gender);
                bpce.setEmp_designation(emp_type);
                bpce.setBase_salary(Double.parseDouble(baseSalary1));
                bpce.setCommission_rate(Double.parseDouble(commissionRate));
                bpce.setGross_sale(Double.parseDouble(grossSale));
                Log.e("Employee", "registerNewEmployee: "+bpce);*/
                break;
        }
    }

    public void showDatePicker(View view) {

        Calendar calendar = Calendar.getInstance();
        //get the current date from calender
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog =
                new DatePickerDialog(this,this, year, month, day);
                datePickerDialog.show();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, dayOfMonth);
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        dob = sdf.format(calendar.getTime());
        dobET.setText(dob);

    }
}
