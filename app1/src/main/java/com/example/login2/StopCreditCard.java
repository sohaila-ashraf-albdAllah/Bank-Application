package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import Model.User;

public class StopCreditCard extends AppCompatActivity {
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop_credit_card);
        DatabaseHandler db=new DatabaseHandler(this);
        EditText stopcreditCardNumber=findViewById(R.id.stop_creditcard_id);
        Button stop=findViewById(R.id.stop_button_id);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean flag=true;
                String stopcreditCardNumberstring=stopcreditCardNumber.getText().toString();
                if(stopcreditCardNumberstring.isEmpty())
                {
                    flag=false;
                    Toast.makeText(getApplicationContext(),"Please Enter The CreditCard Number",Toast.LENGTH_LONG).show();
                }
                User user=db.getUser(username);
                if(flag)
                {
                    if(!db.checkCreditCard(user.getAccnumber(),stopcreditCardNumberstring))
                    {
                        flag=false;
                        Toast.makeText(getApplicationContext(),"invalid CreditCard Number",Toast.LENGTH_LONG).show();
                    }
                }
                if(flag)
                {
                    db.deleteCreditCard(stopcreditCardNumberstring);
                    Toast.makeText(getApplicationContext(),"Your CreditCrad Has Stopped",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}