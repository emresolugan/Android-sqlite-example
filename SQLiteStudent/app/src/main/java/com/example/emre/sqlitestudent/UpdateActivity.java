package com.example.emre.sqlitestudent;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by emre on 5.5.2017.
 */

public class UpdateActivity extends AppCompatActivity {
    Button onayinfo,redinfo;
    TextView tvNameSurname,tvAge,tvGender,tvClass,txtGPA;
    EditText etName,etSurname,etAge;
    Spinner spGender,spClass;
    SeekBar seekBarGPA;
    TextView tvGPA;
    Double sonuc=0.00;
    Integer selectetEmployeePosition=-1;

    ArrayList<Employee> employees = new ArrayList<>();
    EmployeeDBContext employeeDBContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        this.employeeDBContext = new EmployeeDBContext(getApplicationContext());
        employees = employeeDBContext.GetAllEmployees();
        onayinfo= (Button) findViewById(R.id.BtnOnay);
        redinfo= (Button) findViewById(R.id.BtnCancel);
        etName=(EditText)findViewById(R.id.etName);
        etSurname=(EditText)findViewById(R.id.etSurname);
        etAge=(EditText)findViewById(R.id.etAge);
        spGender = (Spinner) findViewById(R.id.spGender);
        spClass = (Spinner) findViewById(R.id.spClass);
        seekBarGPA=(SeekBar)findViewById(R.id.seekBarGPA);
        tvGPA=(TextView)findViewById(R.id.tvGPA);

        showCurrentRecord();



        seekBarGPA.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                sonuc=Double.parseDouble(String.valueOf(progress));
                sonuc=sonuc/100;
                tvGPA.setText(sonuc.toString());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        onayinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Kontrol())
                {
                    String name = etName.getText().toString();
                    String surname = etSurname.getText().toString();
                    Integer age = Integer.parseInt(etAge.getText().toString());
                    Double gpa = Double.parseDouble(tvGPA.getText().toString());
                    String gender = spGender.getSelectedItem().toString();
                    Long sclass=Long.parseLong(spClass.getSelectedItem().toString());

                    Employee employee = employees.get(selectetEmployeePosition);
                    employee.setName(name);
                    employee.setSurname(surname);
                    employee.setAge(age);
                    employee.setGpa(gpa);
                    employee.setGender(gender);
                    employee.setSinif(sclass);

                    employeeDBContext.UpdateEmployee(employee);
                    restartPage();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tüm kısımları doldurduğunuzdan emin olunuz!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        redinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartPage();
                finish();
            }
        });

    }


    private void showCurrentRecord() {

        Intent myIntent=getIntent();

        selectetEmployeePosition=myIntent.getIntExtra("position",0);
        Employee employee = employees.get(selectetEmployeePosition);

        etName.setText(employee.getName().toString());
        etSurname.setText(employee.getSurname().toString());
        etAge.setText(employee.getAge().toString());
        if(employee.getGender().toString()=="Bay"){
            spGender.setSelection(0);
        }
        else {
            spGender.setSelection(1);
        }
        if(employee.getSinif().toString()=="1"){
            spGender.setSelection(0);
        }
        else if(employee.getSinif().toString()=="2") {
            spGender.setSelection(1);
        }
        else if(employee.getSinif().toString()=="3") {
            spGender.setSelection(2);
        }
        else if(employee.getSinif().toString()=="3") {
            spGender.setSelection(3);
        }

    }

    private boolean Kontrol() {
        if(etName.getText().toString().isEmpty() ||
                etSurname.getText().toString().isEmpty() ||
                etAge.getText().toString().isEmpty())
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    private void restartPage() {
        //SIFIRLA
        etName.setText("");
        etSurname.setText("");
        etAge.setText("");
        seekBarGPA.setProgress(0);
        spGender.setSelection(0);
        spClass.setSelection(0);
    }


}
