package com.gtappdevelopers.musicplayer1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {


    private ImageView img1;
    private TextView textView;
    private ProgressBar progressBar;
    private SeekBar seekBar;
    private int progress;
    private boolean fromUser;
    private Switch sw;
    private MediaPlayer mediaPlayer;
    private AudioManager audioManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sw = (Switch) findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);
        img1 = (ImageView) findViewById(R.id.img1);
        //
        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        seekBar = (SeekBar) findViewById(R.id.SeekBar);
        seekBar.setProgress(100);
        seekBar.setOnSeekBarChangeListener(this);
        //
        mediaPlayer = MediaPlayer.create(this, R.raw.skype_sound);
        mediaPlayer.start();
        //
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);


    }


    @Override
    public void onCheckedChanged(CompoundButton bottonView, boolean isChecked) {
        if (isChecked) {
            img1.setVisibility(View.VISIBLE);
            mediaPlayer = MediaPlayer.create(this, R.raw.skype_sound);
            mediaPlayer.start();
        } else {
            img1.setVisibility(View.INVISIBLE);

            mediaPlayer.stop();
        }

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        this.seekBar = seekBar;
        this.progress = progress;
        this.fromUser = fromUser;
        float alpha = (float) progress / 100;
        img1.setAlpha(alpha);
        progressBar.setProgress(progress);
        textView.setText("" + progress + "%");
        audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);


    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onPause() {
        super.onPause();
        mediaPlayer.release();
    }
}


