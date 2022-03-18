package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Options extends AppCompatActivity {
     String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        Button transaction=findViewById(R.id.transaction);
        Button transfer=findViewById(R.id.transfer);
        Button about=findViewById(R.id.about);
        Button request=findViewById(R.id.requests);
        Button profile=findViewById(R.id.profile);

        transaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Options.this,Transactions.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Options.this,Transfers.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Options.this,About.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        request.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Options.this,Request2.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Options.this,Profile.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
        });
    }
}