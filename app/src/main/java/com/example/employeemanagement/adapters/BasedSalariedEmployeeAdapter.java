package com.example.employeemanagement.adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.employeemanagement.EmployeeDetailsActivity;
import com.example.employeemanagement.MainActivity;
import com.example.employeemanagement.R;
import com.example.employeemanagement.entities.BasedSalariedEmployee;
import com.example.employeemanagement.roomdb.EmployeeDB;

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
                final BasedSalariedEmployee bse = empList.get(position);
                final long empId = bse.getEmpID();
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.employee_row_menu,popupMenu.getMenu());
                popupMenu.show();
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.row_item_details:
                                Intent intent = new Intent(context, EmployeeDetailsActivity.class);
                                intent.putExtra("id", empId);
                                context.startActivity(intent);
                                break;

                            case R.id.row_item_edit:
                                Intent intent1 = new Intent(context, MainActivity.class);
                                intent1.putExtra("id", empId);
                                context.startActivity(intent1);
                                break;

                            case R.id.row_item_delete:
                                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                builder.setIcon(R.drawable.ic_delete_forever_black_24dp);
                                builder.setTitle("Delete item");
                                builder.setMessage("Are you sure to delete this item?");
                                builder.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        final int deletedItem = EmployeeDB.getInstance(context)
                                                .getBasiedSalariedEmployeeDAO()
                                                .deleteBasiedSalariedEmployee(bse);
                                        if (deletedItem > 0){
                                            empList.remove(bse);
                                            notifyDataSetChanged();
                                            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                builder.setPositiveButton("Cancel",null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
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
