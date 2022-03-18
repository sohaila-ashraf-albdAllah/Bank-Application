package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    TextView text;
    Button button;
    EditText username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHandler db=new DatabaseHandler(this);
        username=findViewById(R.id.firstname);
        password=findViewById(R.id.password);

        text=(TextView) findViewById(R.id.textView3) ;
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });

       TextView forget=findViewById(R.id.textView2);
        forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,Forget_Password.class);
                startActivity(intent);
            }
        });


    button = (Button) findViewById(R.id.button);
    button.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String usernamestring=username.getText().toString();
            String passwordstring=password.getText().toString();
            boolean flag =true;
            if(usernamestring.isEmpty()||passwordstring.isEmpty())
            {
                Toast.makeText(getApplicationContext(),"Please Enter All Required Information",Toast.LENGTH_LONG).show();
                flag=false;
            }
            if(flag)
            {
                flag=db.checkLogin(usernamestring,passwordstring);
            }
            if(flag)
            {
                Intent intent= new Intent(MainActivity.this,Options.class);
                intent.putExtra("username",usernamestring);
                startActivity(intent);
            }
            else
            {
                Toast.makeText(getApplicationContext(), "Log In Failed", Toast.LENGTH_LONG).show();
            }
        }
    });




    }
}

