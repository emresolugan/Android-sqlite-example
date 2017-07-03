package com.example.emre.sqlitestudent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Information extends AppCompatActivity {

    TextView tvName,tvSurname,tvAge,tvGender,tvClass,tvGPA;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        tvName=(TextView)findViewById(R.id.txtName);
        tvSurname= (TextView) findViewById(R.id.txtSURNAME);
        tvAge=(TextView)findViewById(R.id.txtAGE);
        tvGender=(TextView)findViewById(R.id.txtGENDER);
        tvClass=(TextView)findViewById(R.id.txtCLASS);
        tvGPA=(TextView)findViewById(R.id.txtGPA);
        btn= (Button) findViewById(R.id.button);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
            }
        });

        Intent myIntent=getIntent();


        String name=myIntent.getStringExtra("name");
        String surname=myIntent.getStringExtra("surname");
        String age=myIntent.getStringExtra("age");
        String gpa=myIntent.getStringExtra("gpa");
        String gender=myIntent.getStringExtra("gender");
        String Sclass=myIntent.getStringExtra("class");

        tvName.setText(name);
        tvSurname.setText(surname);
        tvAge.setText(age);
        tvGPA.setText(gpa);
        tvGender.setText(gender);
        tvClass.setText(Sclass);




    }

}