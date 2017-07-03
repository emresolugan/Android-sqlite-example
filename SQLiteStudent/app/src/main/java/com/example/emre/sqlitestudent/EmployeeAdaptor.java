package com.example.emre.sqlitestudent;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by emre on 5.5.2017.
 */

public class EmployeeAdaptor extends BaseAdapter {

    private LayoutInflater inflater;
    private List<Employee> employees;

    public EmployeeAdaptor(Activity activity, List<Employee> employees) {
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.employees = employees;
    }

    @Override
    public int getCount() {
        return this.employees.size();
    }

    @Override
    public Employee getItem(int position) {
        return employees.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView;

        rowView = inflater.inflate(R.layout.employee_visualize,null);

        TextView tvNameSurname = (TextView) rowView.findViewById(R.id.tvNameSurname);
        TextView tvAge = (TextView) rowView.findViewById(R.id.tvAge);
        TextView tvGpa = (TextView) rowView.findViewById(R.id.tvGPA);
        TextView tvGender = (TextView) rowView.findViewById(R.id.tvGender);

        Employee employee = employees.get(position);

        tvNameSurname.setText(employee.getName() + " " + employee.getSurname());
        tvAge.setText(employee.getAge().toString());
        tvGpa.setText(employee.getGpa().toString());
        tvGender.setText(employee.getGender());

        return rowView;
    }
}

