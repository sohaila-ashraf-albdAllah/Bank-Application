package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;

import java.util.Vector;

import Model.User;

public class Transactions extends AppCompatActivity {
    boolean cf=true;
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transactions);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.anima, R.anim.ssss);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        DatabaseHandler db=new DatabaseHandler(this);
        User user=db.getUser(username);
        Cursor cursor=db.getUserCreditCards(user.getAccnumber());


        ft.replace(R.id.placeholder, card1.newInstance(user.getAccnumber(),cursor.getString(0)));
        ft.commit();
        View view=findViewById(R.id.button3);
        view.findViewById(R.id.button3).setOnClickListener(view1 -> {
            FragmentTransaction ft1 = getSupportFragmentManager().beginTransaction();
            ft1.setCustomAnimations(R.anim.anima, R.anim.ssss);

            if (cf) {
                if(cursor.isLast())
                    cursor.moveToFirst();
                else {
                    cursor.moveToNext();
                }
                ft1.replace(R.id.placeholder,card2.newInstance(user.getAccnumber(), cursor.getString(0)));
                cf=false;
            }else {
                if(cursor.isLast())
                    cursor.moveToFirst();
                else {
                    cursor.moveToNext();
                }
                ft1.replace(R.id.placeholder, card1.newInstance(user.getAccnumber(),cursor.getString(0)));
                cf=true;
            }
            ft1.commit();
        });
        view=findViewById(R.id.button4);
        view.findViewById(R.id.button4).setOnClickListener(view12 -> {
            FragmentTransaction ft12 = getSupportFragmentManager().beginTransaction();
            ft12.setCustomAnimations(R.anim.popa, R.anim.pops);

            if (cf) {
                if(cursor.isFirst())
                    cursor.moveToLast();
                else {
                    cursor.moveToPrevious();
                }
                ft12.replace(R.id.placeholder, card2.newInstance(user.getAccnumber(), cursor.getString(0)));
                cf=false;
            }else {
                if(cursor.isFirst())
                    cursor.moveToLast();
                else {
                    cursor.moveToPrevious();
                }
                ft12.replace(R.id.placeholder, card1.newInstance(user.getAccnumber(),cursor.getString(0)));
                cf=true;
            }

            ft12.commit();

        });
    }
}