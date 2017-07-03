package com.example.emre.sqlitestudent;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnadd,btnuptade,btndelete,btndeleteall,btninfo;
    ListView lv;
    int selectedEmployeePosition = -1;
    ArrayList<Employee> employees = new ArrayList<>();



    EmployeeAdaptor employeeAdaptor;

    EmployeeDBContext employeeDBContext;

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onResume() {
        super.onResume();

        this.updateListViewContent();



    }

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.employeeDBContext = new EmployeeDBContext(getApplicationContext());
        btnadd= (Button) findViewById(R.id.btnAdd);
        btnuptade= (Button) findViewById(R.id.btnUptade);
        btndelete= (Button) findViewById(R.id.btnDelete);
        btndeleteall= (Button) findViewById(R.id.btnDeleteAll);
        btninfo= (Button) findViewById(R.id.btnInfo);
        lv= (ListView) findViewById(R.id.lv);





        btnadd.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
            @Override
            public void onClick(View view) {
                Intent myIntent=new Intent(getApplicationContext(),AddActivity.class);
                startActivity(myIntent);
                selectedEmployeePosition = -1;


            }

        });
        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedEmployeePosition == -1){
                    Toast.makeText(getApplicationContext(), "Select an Employeee!", Toast.LENGTH_LONG).show();
                    return;
                }
                Intent myIntent=new Intent(getApplicationContext(),Information.class);


                Employee employee = employees.get(selectedEmployeePosition);
                myIntent.putExtra("name",employee.getName().toString());
                myIntent.putExtra("surname",employee.getSurname().toString());
                myIntent.putExtra("age",employee.getAge().toString());
                myIntent.putExtra("gender",employee.getGender().toString());
                myIntent.putExtra("class",employee.getClass().toString());
                myIntent.putExtra("gpa",employee.getGpa().toString());

                startActivity(myIntent);

                selectedEmployeePosition = -1;
            }
        });
        btnuptade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(selectedEmployeePosition == -1){
                    Toast.makeText(getApplicationContext(), "Select an Employee!", Toast.LENGTH_LONG).show();
                    return;
                }

                Intent myIntent=new Intent(getApplicationContext(),UpdateActivity.class);
                myIntent.putExtra("position",selectedEmployeePosition);
                startActivity(myIntent);
                selectedEmployeePosition = -1;
            }
        });
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedEmployeePosition == -1){
                    Toast.makeText(MainActivity.this, "Select an Employee!", Toast.LENGTH_SHORT).show();
                    return;
                }

                Employee employee = employees.get(selectedEmployeePosition);
                employeeDBContext.DeleteEmployee(employee);

                selectedEmployeePosition = -1;
                updateListViewContent();
            }
        });
        btndeleteall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employeeDBContext.RemoveAllEmployees();
                selectedEmployeePosition = -1;
                updateListViewContent();
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedEmployeePosition = i;
                updateListViewContent();

            }
        });
    }

    private void updateListViewContent(){
        employees = employeeDBContext.GetAllEmployees();

        employeeAdaptor = new EmployeeAdaptor(this,employees);

        lv.setAdapter(employeeAdaptor);
    }

    public void updateBoxesBySelectedEmployee(){
        Employee employee = employees.get(selectedEmployeePosition);


    }
}

