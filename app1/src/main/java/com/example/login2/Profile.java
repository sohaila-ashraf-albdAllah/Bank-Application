package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Vector;

import Model.User;

public class Profile extends AppCompatActivity {
    Button username,email,phone,address,save;
    TextView editusername,editemail,editphone,editaddress;
    String userName="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DatabaseHandler db=new DatabaseHandler(this);
        setContentView(R.layout.activity_profile);
        Bundle extras=getIntent().getExtras();
        userName=extras.getString("username");
        username=findViewById(R.id.usernameedit);
        email=findViewById(R.id.Emailedit);
        address=findViewById(R.id.addressedit);
        phone=findViewById(R.id.phoneedit);
        save=findViewById(R.id.Save);
        editphone=findViewById(R.id.phone);
        editusername=findViewById(R.id.username);
        editemail=findViewById(R.id.Email);
        editaddress=findViewById(R.id.address);
        User user=db.getUser(userName);
        editusername.setText(user.getUsername());
        editphone.setText(user.getPhone());
        editemail.setText(user.getEmail());
        editaddress.setText(user.getAdress());

        username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editusername.setEnabled(true);
                save.setVisibility(View.VISIBLE);

            }
        });
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editemail.setEnabled(true);
                save.setVisibility(View.VISIBLE);
            }
        });
        address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editaddress.setEnabled(true);
                save.setVisibility(View.VISIBLE);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editphone.setEnabled(true);
                save.setVisibility(View.VISIBLE);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Vector<String> cols = new Vector<String>();
                Vector<String> values = new Vector<String>();
                if (editusername.isEnabled()){
                    cols.add("username");
                    values.add(editusername.getText().toString());
                    editusername.setEnabled(false);
                }
                if (editphone.isEnabled()){
                    cols.add("phone");
                    values.add(editphone.getText().toString());
                    editphone.setEnabled(false);
                }
                if (editaddress.isEnabled()){
                    cols.add("adress");
                    values.add(editaddress.getText().toString());
                    editaddress.setEnabled(false);
                }
                if (editemail.isEnabled()){
                    cols.add("Email");
                    values.add(editemail.getText().toString());
                    editemail.setEnabled(false);
                }
                db.updateUserData(cols,values,userName);
                Toast.makeText(getApplicationContext(),"New Data Saved",Toast.LENGTH_LONG).show();
                save.setVisibility(View.INVISIBLE);
            }
        });
    }
}