package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import Model.User;

public class Request2 extends AppCompatActivity {
    Button laonButton , stopCardButton , deleteAccButton;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        DatabaseHandler db=new DatabaseHandler(this);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        laonButton = findViewById(R.id.loanId);
        stopCardButton = findViewById(R.id.stopCardId);
        deleteAccButton = findViewById(R.id.deleteAccountId);

        laonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Request2.this , Loan.class);
                startActivity(intent);
            }
        });
        stopCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Request2.this , StopCreditCard.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        deleteAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user=db.getUser(username);
                db.deleteuserCreditCards(user.getAccnumber());
                db.deleteuser(user.getAccnumber());
                Intent intent = new Intent(Request2.this , MainActivity.class);
                startActivity(intent);
            }
        });
    }
}