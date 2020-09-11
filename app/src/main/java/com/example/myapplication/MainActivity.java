package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button play,pause;
    ImageView volumeUp,volumeDown,anim;
    SeekBar timelineSong;
    MediaController videoController;
    VideoView video;
    MediaPlayer mediaPlayer;
    AudioManager audioManager;
    int volumeMaximum,volumeMinimum,volumeCurrent,volumeRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video = findViewById(R.id.videoView);
        play = findViewById(R.id.playButton);
        pause = findViewById(R.id.puseButton);

        volumeDown=findViewById(R.id.volumeDown);
        volumeUp= findViewById(R.id.volumeUp);
        anim = findViewById(R.id.styleRotate);

        timelineSong = findViewById(R.id.seekBar);

        anim.animate().rotation(36000000).setDuration(10000).start();

        videoController = new MediaController(MainActivity.this);
        Uri videoUri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video);
        video.setVideoURI(videoUri);
        video.setMediaController(videoController);
        videoController.setAnchorView(video);
        mediaPlayer = MediaPlayer.create(this,R.raw.audio);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        volumeUp.setOnClickListener(this);
        volumeDown.setOnClickListener(this);

        audioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
        volumeCurrent = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        volumeMaximum = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        volumeMinimum = audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC);
        volumeRate= 20 ;

    }


    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.playButton:
                video.start();
                mediaPlayer.start();
                break;
            case R.id.puseButton:
                video.pause();
                mediaPlayer.pause();
                break;
            case R.id.volumeUp:
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumeCurrent+volumeRate,0);
                break;
            case R.id.volumeDown:
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumeCurrent-volumeRate,0);
                break;
        }

    }
}