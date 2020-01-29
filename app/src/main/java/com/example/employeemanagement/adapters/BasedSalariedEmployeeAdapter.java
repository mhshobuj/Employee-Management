package com.example.employeemanagement.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagement.R;
import com.example.employeemanagement.entities.BasedSalariedEmployee;

import java.util.List;

public class BasedSalariedEmployeeAdapter extends RecyclerView.Adapter<BasedSalariedEmployeeAdapter.BasedSalariedEmployeeViewHolder> {

    private Context context;
    private List<BasedSalariedEmployee> empList;

    public BasedSalariedEmployeeAdapter(Context context, List<BasedSalariedEmployee> empList) {
        this.context = context;
        this.empList = empList;
    }

    @NonNull
    @Override
    public BasedSalariedEmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.employee_list,parent,false);
        return new BasedSalariedEmployeeViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BasedSalariedEmployeeViewHolder holder, final int position) {
        holder.nameTV.setText(empList.get(position).getEmp_name());
        holder.phoneTV.setText(empList.get(position).getEmp_phone());
        holder.genderTV.setText(empList.get(position).getEmp_gender());
        holder.menuTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BasedSalariedEmployee bse = empList.get(position);
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.employee_row_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.row_item_details:
                                Toast.makeText(context, "Details", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.row_item_edit:
                                Toast.makeText(context, "Edit", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.row_item_delete:
                                Toast.makeText(context, "Delete", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        return false;
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return empList.size();
    }

    public class BasedSalariedEmployeeViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV, phoneTV, genderTV, menuTV;
        public BasedSalariedEmployeeViewHolder(@NonNull View itemView) {

            super(itemView);
            nameTV = itemView.findViewById(R.id.row_empName);
            phoneTV = itemView.findViewById(R.id.row_empPhone);
            genderTV = itemView.findViewById(R.id.row_empGender);
            menuTV = itemView.findViewById(R.id.row_menu);

        }
    }
}
