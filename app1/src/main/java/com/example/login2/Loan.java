package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Model.Request;

public class Loan extends AppCompatActivity {

    Button saveButton , homeButton;
    TextView name , salary , job , numOfMonths ;
    EditText personName , salaryAns , jobAns , monthsAns ;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loan);

        db = new DatabaseHandler(this);

        name = findViewById(R.id.nameID);
        salary = findViewById(R.id.salaryId);
        job = findViewById(R.id.jobId);
        numOfMonths = findViewById(R.id.numOFmonthsId);

        personName = findViewById(R.id.personNameID);
        salaryAns = findViewById(R.id.salaryAnsId);
        jobAns = findViewById(R.id.jobAnswerId);
        monthsAns = findViewById(R.id.monthsAnsId);

        saveButton = findViewById(R.id.saveId);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String personNameCheck = personName.getText().toString() ;
                String salaryAnsCheck = salaryAns.getText().toString() ;
                String jobAnsCheck = jobAns.getText().toString() ;
                String monthsAnsCheck = monthsAns.getText().toString() ;

                // int salaryOb = Integer.valueOf(salaryAnsCheck);
                // int monthsOb = Integer.valueOf(monthsAnsCheck);

                if (personNameCheck.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please write your Name ",Toast.LENGTH_LONG).show();
                }
                else if (salaryAnsCheck.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please write your Salary ",Toast.LENGTH_LONG).show();
                }
                else if (jobAnsCheck.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please write your Job ",Toast.LENGTH_LONG).show();
                }
                else if (monthsAnsCheck.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please put The Number of months ",Toast.LENGTH_LONG).show();
                }
                else {
                    db.newRequest( new Request( jobAnsCheck ,  personNameCheck , Integer.parseInt(salaryAnsCheck) , Integer.parseInt(monthsAnsCheck)));
                   /* List<Person> personList = db.AllData();
                    for (Person p : personList)
                    {
                        String myin = " name " + p.getName() + " job" + p.getJob() + " salary " + p.getSalary() + " number of months " + p.getNumofmonths();
                        Log.d("ALL data ",myin);
                    }*/
                    Toast.makeText(getApplicationContext(), "Your information has been saved", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}