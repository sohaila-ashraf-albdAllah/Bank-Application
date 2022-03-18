package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.User;

public class Register extends AppCompatActivity {

    EditText username,password,address,Email,age,phone,firstname,lastname;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        DatabaseHandler db =new DatabaseHandler(this);
        username=findViewById(R.id.username);
        password=findViewById(R.id.password);
        address=findViewById(R.id.Address);
        Email=findViewById(R.id.Email);
        age=findViewById(R.id.age);
        phone=findViewById(R.id.phone);
        firstname=findViewById(R.id.firstname);
        lastname=findViewById(R.id.lastname);
        register=findViewById(R.id.registerbutton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usernamestring=username.getText().toString();
                String passwordstring=password.getText().toString();
                String addressstring=address.getText().toString();
                String emailstring=Email.getText().toString();
                String agestring=age.getText().toString();
                String phonestring=phone.getText().toString();
                String firstnamestring=firstname.getText().toString();
                String lastnamestring=lastname.getText().toString();
                boolean flag =true;
                if(usernamestring.isEmpty()||passwordstring.isEmpty()||addressstring.isEmpty()
                        ||emailstring.isEmpty()||agestring.isEmpty()||phonestring.isEmpty()
                        ||firstnamestring.isEmpty()||lastnamestring.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Please Enter All Required Information",Toast.LENGTH_LONG).show();
                    flag=false;
                }
                User user=null;
                if(flag)
                {
                     user=new User(usernamestring,passwordstring,firstnamestring+" "+lastnamestring
                            ,addressstring,emailstring,phonestring,agestring);
                    flag=db.checkregister(user.getUsername(),user.getEmail(),user.getPhone());
                }
                if(flag)
                {
                    db.NewUser(user);
                    Toast.makeText(getApplicationContext(),"info saved",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(Register.this,Options.class);
                    intent.putExtra("username",user.getUsername());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"info Invalid",Toast.LENGTH_LONG).show();
                }
            }
        });

       // x.NewUser();

    }
}