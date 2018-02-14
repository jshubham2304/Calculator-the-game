package com.example.lenovo.button3d;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tushar on 6/11/17.
 */

public class scoreActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_layout);
        TextView level1 = (TextView) findViewById(R.id.level1);
        TextView level2 = (TextView) findViewById(R.id.level2);
        TextView level3 = (TextView) findViewById(R.id.level3);
        TextView level4 = (TextView) findViewById(R.id.level4);
        TextView level5 = (TextView) findViewById(R.id.level5);
        TextView level6 = (TextView) findViewById(R.id.level6);
        TextView level7 = (TextView) findViewById(R.id.level7);
        TextView level8 = (TextView) findViewById(R.id.level8);
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bclick);
        mp.start();
        List<Integer> list = getIntent().getExtras().getIntegerArrayList("code");
        level1.setText(""+list.get(0));
        level2.setText(""+list.get(1));
        level3.setText(""+list.get(2));
        level4.setText(""+list.get(3));
        level5.setText(""+list.get(4));
        level6.setText(""+list.get(5));
        level7.setText(""+list.get(6));
        level8.setText(""+list.get(7));


    }
        public void back(View view)
    {
        final MediaPlayer mp = MediaPlayer.create(this, R.raw.bclick);
        mp.start();
       Intent intent = new Intent(scoreActivity.this,SettingActivity.class);
       startActivity(intent);
    }
}