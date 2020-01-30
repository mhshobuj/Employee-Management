package com.example.employeemanagement.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.employeemanagement.entities.BasedSalariedEmployee;

import java.util.List;

@Dao
public interface BasiedSalariedEmployeeDAO {

    @Insert
    long insertBasiedSalariedEmployee(BasedSalariedEmployee bse);

    @Update
    int updateBasiedSalariedEmployee(BasedSalariedEmployee bse);

    @Delete
    int deleteBasiedSalariedEmployee(BasedSalariedEmployee bse);

    @Query("select * from TBL_BASED_SALARIED_EMP")
    List<BasedSalariedEmployee> getAllBasedSalariedEmployee();

    @Query("select * from tbl_based_salaried_emp where empID like :empId")
    BasedSalariedEmployee getBaseSalariedEmployeeID(long empId);
}
