package com.example.emre.sqlitestudent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {


    EmployeeDBContext employeeDBContext;
    Button onay,red;
    EditText etName,etSurname,etAge;
    Spinner spGender,spClass;
    SeekBar seekBarGPA;
    TextView tvGPA;
    Double sonuc=0.00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        etName=(EditText)findViewById(R.id.etName);
        etSurname=(EditText)findViewById(R.id.etSurname);
        etAge=(EditText)findViewById(R.id.etAge);
        spGender = (Spinner) findViewById(R.id.spGender);
        spClass = (Spinner) findViewById(R.id.spClass);
        seekBarGPA=(SeekBar)findViewById(R.id.seekBarGPA);
        tvGPA=(TextView)findViewById(R.id.tvGPA);
        onay= (Button) findViewById(R.id.BtnOnay);
        red= (Button) findViewById(R.id.BtnCancel);

        this.employeeDBContext = new EmployeeDBContext(getApplicationContext());

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
        red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                restartPage();
                finish();
            }
        });
        onay.setOnClickListener(new View.OnClickListener() {
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
                    Employee employee = new Employee(name,surname,age,gpa,gender,sclass);
                    employeeDBContext.AddEmployee(employee);
                    restartPage();
                    finish();
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Tüm kısımları doldurduğunuzdan emin olunuz!", Toast.LENGTH_SHORT).show();
                }

            }
        });
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

        etName.setText("");
        etSurname.setText("");
        etAge.setText("");
        seekBarGPA.setProgress(0);
        spGender.setSelection(0);
        spClass.setSelection(0);
    }
}
