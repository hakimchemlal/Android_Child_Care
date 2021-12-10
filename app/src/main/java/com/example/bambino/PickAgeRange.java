package com.example.bambino;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.bambino.R;

public class PickAgeRange extends AppCompatActivity  {
    Button submition;
    TextView age;
    int minage = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pick_age_page);
        submition = (Button) findViewById(R.id.btnSubmit);
        age = (TextView) findViewById(R.id.inputAge);
        submition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num = Integer.parseInt(age.getText().toString());
                if (num >= 0 && num < 6){
                    minusThanSeven();
                }else if (num >= 6 && num <= 10){
                    moreThanSeven();
                }else{
                    Toast.makeText(PickAgeRange.this, "Age Must be between 0 and 10", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void increaseAge(View view) {
        minage = minage + 1;
        display(minage);

    }public void decreaseAge(View view) {
        minage = minage - 1;
        display(minage);
    }

    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.inputAge);
        displayInteger.setText("" + number);
    }

    private void moreThanSeven(){
        Intent openHome1 = new Intent(this, Home.class);
        startActivity(openHome1);
        this.finish();
    }

    private void minusThanSeven(){
        Intent openHome2 = new Intent(this, Home.class);
        startActivity(openHome2);
        this.finish();
    }

}