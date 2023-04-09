package com.smartherd.androiddevpractice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.accessibilityservice.FingerprintGestureController;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playAgain=findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
    }
    int playerNm=0; //o:o 1:X, 2:Empty
    int imgTapped[]={2,2,2,2,2,2,2,2,2};

    int winPositions[][]={{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};
    public static int count=0;
    boolean gameActive=true;

    public void onTap(View view) {
        if (gameActive) {
            ImageView img = (ImageView) view;
            int imgTag = Integer.parseInt(img.getTag().toString());
            if (imgTapped[imgTag] == 2) {
                count++;
                imgTapped[imgTag] = playerNm;
                img.setTranslationY(-1000);
                if (playerNm == 0) {
                    img.setImageResource(R.drawable.oimg);
                    playerNm = 1;
                    TextView txt = findViewById(R.id.playerNameView);
                    txt.setText("X's Turn");
                } else {
                    img.setImageResource(R.drawable.ximg);
                    playerNm = 0;
                    TextView txt = findViewById(R.id.playerNameView);
                    txt.setText("O's Turn");
                }
                img.animate().translationYBy(1000).setDuration(200);
            }
            int draw = 1;
            for (int[] winPose : winPositions) {
                draw = 0;
                if (imgTapped[winPose[0]] == imgTapped[winPose[1]] && imgTapped[winPose[1]] == imgTapped[winPose[2]] && imgTapped[winPose[0]] != 2) {
                    String winner;
                    gameActive = false;
                    if (imgTapped[winPose[0]] == 0)
                        winner = "O had won";
                    else
                        winner = "X had won";
                    TextView txt = findViewById(R.id.playerNameView);
                    txt.setText(winner);
                    Button playAgain = findViewById(R.id.playAgain);
                    playAgain.setVisibility(View.VISIBLE);
                }
            }
            if (count == 9) {
                TextView txt = findViewById(R.id.playerNameView);
                txt.setText("Shit,man.Draw!");
                Button playAgain = findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);
                gameActive=false;
            }
        }
        else{
            Toast.makeText(MainActivity.this, "You gotta press the Play Again button, you dumb bitch.", Toast.LENGTH_SHORT).show();
        }
    }
    public void playAgain(View view){
        Button playAgain = findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        gameActive=true;
        TextView txt = findViewById(R.id.playerNameView);
        txt.setText("O's Turn");
        playerNm=0;
        for(int i=0;i<9;i++)
            imgTapped[i]=2;
        count=0;
        ((ImageView)findViewById(R.id.image0)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image1)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image2)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image3)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image4)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image5)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image6)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image7)).setImageDrawable(null);
        ((ImageView)findViewById(R.id.image8)).setImageDrawable(null);
    }
}