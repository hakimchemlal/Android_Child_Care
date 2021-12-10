package com.example.bambino;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import com.example.bambino.R;

public class Home extends AppCompatActivity {
    ImageView a,b,c,d,e,f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        a=(ImageView)findViewById(R.id.imgHealth);
        b=(ImageView)findViewById(R.id.imgVacc);
        c=(ImageView)findViewById(R.id.imgSleep);
        d=(ImageView)findViewById(R.id.imgFood);
        e=(ImageView)findViewById(R.id.imgGuide);
        f=(ImageView)findViewById(R.id.imgBaby);
        a.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityHealthCare();
            }
        });
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityVaccine();
            }
        });
        c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivitySleep();
            }
        });
        d.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityFood();
            }
        });
        e.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityGuide();
            }
        });
        f.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                openActivityBaby();
            }
        });
    }
    public  void openActivityHealthCare(){
        Intent intent = new Intent(this, HealthCare.class);
        startActivity(intent);
    }
    public  void openActivityVaccine(){
        Intent intent = new Intent(this, Vaccine.class);
        startActivity(intent);
    }
    public  void openActivitySleep(){
        Intent intent = new Intent(this, Sleep.class);
        startActivity(intent);
    }
    public  void openActivityFood(){
        Intent intent = new Intent(this, Food.class);
        startActivity(intent);
    }
    public  void openActivityGuide(){
        Intent intent = new Intent(this, Guide.class);
        startActivity(intent);
    }
    public  void openActivityBaby(){
        Intent intent = new Intent(this, Baby.class);
        startActivity(intent);
    }
}