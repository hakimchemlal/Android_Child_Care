package com.example.bambino;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import com.example.bambino.R;


public class Sleep extends AppCompatActivity {
    MediaPlayer mySong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);
        mySong=MediaPlayer.create(this,R.raw.sleep_music);
    }
    public void playIT(View v){
        mySong.start();
    }
    @Override
    protected void onPause() {
        super.onPause();
        mySong.release();
    }
    public void StopIT(View view) {
        mySong.pause();
    }
}