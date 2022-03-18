package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.User;

public class Forget_Password extends AppCompatActivity {

    private DatabaseHandler db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        EditText accnumber=findViewById(R.id.accnumber_id);
        EditText newpassword=findViewById(R.id.newpassword_id);
        EditText confirmpassword=findViewById(R.id.confirmpassword_id);
        EditText username=findViewById(R.id.edittext_username_id);

        db=new DatabaseHandler(this);

        Button save=findViewById(R.id.save_id);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=true;
                String accnumberstring=accnumber.getText().toString();
                String newpasswordstring=newpassword.getText().toString();
                String confirmpasswordstring=confirmpassword.getText().toString();
                String usernamestring=username.getText().toString();

                if(accnumberstring.isEmpty()||newpasswordstring.isEmpty()||confirmpasswordstring.isEmpty()||usernamestring.isEmpty())
                {
                    flag=false;
                    Toast.makeText(getApplicationContext(), "Complete Required Information", Toast.LENGTH_LONG).show();
                }
                if(flag)
                {
                    if(!(db.checkUser(accnumberstring,usernamestring)))
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(), "Invalid User Name and Account Number", Toast.LENGTH_LONG).show();
                    }
                }
                User user=db.getUser(usernamestring);
                if(flag)
                {
                    if(user.getPassword().equals(newpasswordstring))
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(), "You Have Entered The Old Password", Toast.LENGTH_LONG).show();
                    }
                }
                if(flag)
                {
                    if(!confirmpasswordstring.equals(newpasswordstring))
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(), "You Have Re-entered Wrong Password", Toast.LENGTH_LONG).show();
                    }
                }
                if(flag)
                {
                    db.updatepassword(user.getAccnumber(),newpasswordstring);
                    Toast.makeText(getApplicationContext(), "Password Changed", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}