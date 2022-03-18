package com.example.login2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;

public class About extends AppCompatActivity {
    String username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        Bundle extras=getIntent().getExtras();
        username=extras.getString("username");
        ViewGroup container=findViewById(R.id.container);
        Button info=findViewById(R.id.infobutton);
        Button loc=findViewById(R.id.locbutton);
        ScrollView infoscroll=findViewById(R.id.informationscroll);
        ScrollView locscroll=findViewById(R.id.locationscroll);

        info.setOnClickListener(new View.OnClickListener() {
            boolean visable;
            @Override
            public void onClick(View view) {
               TransitionManager.beginDelayedTransition(container);
                visable=!visable;
                infoscroll.setVisibility(visable?View.VISIBLE:View.GONE);

            }
        });
        loc.setOnClickListener(new View.OnClickListener() {
            boolean visable;
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(container);
                visable=!visable;
                locscroll.setVisibility(visable?View.VISIBLE:View.GONE);
            }
        });
    }
}