package com.sanruza.alpak.tamovamo;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import android.os.Handler;
import java.util.logging.LogRecord;

public class GameActivity extends AppCompatActivity {

    //LOCAL SAVE
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    Handler h = new Handler();
    RelativeLayout Layout;
    Boolean ballRight;
    Boolean gameOverCalled = false;

    int scoreValue, highScoreValue;
    Button Play_Button, Retry_Button;
    TextView Score, ScoreOnBoard, HighscoreOnBoard;
    ImageView Logo, GameOver,Ball, ScoreBoard;
    ImageView Pillar1, Pillar2, Pillar3, Pillar4, Pillar5, Pillar6, Pillar7, Pillar8, Pillar9, Pillar10;
    ImageView PillarTop1, PillarTop2, PillarTop3, PillarTop4, PillarTop5, PillarTop6;
    Rect BallRect = new Rect();
    Rect Pillar1Rect = new Rect();
    Rect Pillar2Rect = new Rect();
    Rect Pillar3Rect = new Rect();
    Rect Pillar4Rect = new Rect();
    Rect Pillar5Rect = new Rect();
    Rect Pillar6Rect = new Rect();
    Rect Pillar7Rect = new Rect();
    Rect Pillar8Rect = new Rect();
    Rect Pillar9Rect = new Rect();
    Rect Pillar10Rect = new Rect();
    Rect PillarTop1Rect = new Rect();
    Rect PillarTop2Rect = new Rect();
    Rect PillarTop3Rect = new Rect();
    Rect PillarTop4Rect = new Rect();
    Rect PillarTop5Rect = new Rect();
    Rect PillarTop6Rect = new Rect();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        pref = getApplicationContext().getSharedPreferences("com.example.TamoVamo_preferences.xml", 0);

        onCreateNew();

        Play_Button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                playBtnClicked();
                movement();

            }
        });

    }




    public void playBtnClicked(){
        //Hiding start Screen
        Logo.setVisibility(View.INVISIBLE);
        Play_Button.setVisibility(View.INVISIBLE);
        //Game Elements Visibilty
        Ball.setVisibility(View.VISIBLE);
        Score.setVisibility(View.VISIBLE);
        Pillar1.setVisibility(View.VISIBLE);
        Pillar2.setVisibility(View.VISIBLE);
        Pillar3.setVisibility(View.VISIBLE);
        Pillar4.setVisibility(View.VISIBLE);
        Pillar5.setVisibility(View.VISIBLE);
        Pillar6.setVisibility(View.VISIBLE);
        Pillar7.setVisibility(View.VISIBLE);
        Pillar8.setVisibility(View.VISIBLE);
        Pillar9.setVisibility(View.VISIBLE);
        Pillar10.setVisibility(View.VISIBLE);
        PillarTop1.setVisibility(View.VISIBLE);
        PillarTop2.setVisibility(View.VISIBLE);
        PillarTop3.setVisibility(View.VISIBLE);
        PillarTop4.setVisibility(View.VISIBLE);
        PillarTop5.setVisibility(View.VISIBLE);
        PillarTop6.setVisibility(View.VISIBLE);



        //Positioning
        Pillar1.setX(320);
        Pillar1.setY(668);
        Ball.setX(340);
        Ball.setY(568);

        Pillar2.setX(Pillar1.getX() + 78);
        Pillar2.setY(Pillar1.getY() - 55);
        Pillar3.setX(Pillar2.getX() + 78);
        Pillar3.setY(Pillar2.getY() - 55);

        Pillar4.setX(pillarPlacementX(Pillar3.getX()));        Pillar4.setY(pillarPlacementY(Pillar3.getY()));
        Pillar5.setX(pillarPlacementX(Pillar4.getX()));        Pillar5.setY(pillarPlacementY(Pillar4.getY()));
        Pillar6.setX(pillarPlacementX(Pillar5.getX()));        Pillar6.setY(pillarPlacementY(Pillar5.getY()));
        Pillar7.setX(pillarPlacementX(Pillar6.getX()));        Pillar7.setY(pillarPlacementY(Pillar6.getY()));
        Pillar8.setX(pillarPlacementX(Pillar7.getX()));        Pillar8.setY(pillarPlacementY(Pillar7.getY()));
        Pillar9.setX(pillarPlacementX(Pillar8.getX()));        Pillar9.setY(pillarPlacementY(Pillar8.getY()));
        Pillar10.setX(pillarPlacementX(Pillar9.getX()));        Pillar10.setY(pillarPlacementY(Pillar9.getY()));

        PillarTop1.setX(Pillar2.getX()+10.5f);        PillarTop1.setY(Pillar2.getX() + 10.5f);
        PillarTop2.setX(Pillar3.getX()+10.5f);        PillarTop2.setY(Pillar3.getX() + 10.5f);
        PillarTop3.setX(Pillar4.getX()+10.5f);        PillarTop3.setY(Pillar4.getX()+10.5f);
        PillarTop4.setX(Pillar5.getX()+10.5f);        PillarTop4.setY(Pillar5.getX()+10.5f);
        PillarTop5.setX(Pillar6.getX()+10.5f);        PillarTop5.setY(Pillar6.getX()+10.5f);
        PillarTop6.setX(Pillar7.getX()+10.5f);        PillarTop6.setY(Pillar7.getX()+10.5f);

    }


    public float pillarPlacementX(float x){
        float PillarNewX = 0f;

        int random = (int)(Math.random() * 2 + 1);

        if(random ==1){
            if(x > 600){
                PillarNewX = x - 79;
            }else{
                PillarNewX = x + 78;
            }
        } else {
            if(x<40){
                PillarNewX = x + 78;
            } else {
                PillarNewX = x - 79;
            }
        }

        return PillarNewX;
    }

    public float pillarPlacementY(float y){
        float PillarNewY = 0f;
        PillarNewY = y - 57;
        return PillarNewY;
    }

    public void onCreateNew(){

        //SavedScore
        highScoreValue = pref.getInt("highScoreSaved",0);
        //score
        scoreValue = 0;
        highScoreValue = 0;
        //boolean
        ballRight = true;
        gameOverCalled = false;
        //layout
        Layout = (RelativeLayout) findViewById(R.id.ReLayout);
        //buttons
        Play_Button = (Button) findViewById(R.id.play_button);
        Retry_Button= (Button) findViewById(R.id.retry_button);
        //textViews
        Score = (TextView) findViewById(R.id.score);
        ScoreOnBoard = (TextView) findViewById(R.id.ScoreOnBoard);
        HighscoreOnBoard = (TextView) findViewById(R.id.highscoreOnBoard);
        //Images
        Logo = (ImageView) findViewById(R.id.logo);
        GameOver = (ImageView) findViewById(R.id.GameOver);
        Ball = (ImageView) findViewById(R.id.Ball);
        ScoreBoard = (ImageView) findViewById(R.id.ScoreBoard);
        //Pillars
        Pillar1 = (ImageView) findViewById(R.id.pillar1);
        Pillar2 = (ImageView) findViewById(R.id.pillar2);
        Pillar3 = (ImageView) findViewById(R.id.pillar3);
        Pillar4 = (ImageView) findViewById(R.id.pillar4);
        Pillar5 = (ImageView) findViewById(R.id.pillar5);
        Pillar6 = (ImageView) findViewById(R.id.pillar6);
        Pillar7 = (ImageView) findViewById(R.id.pillar7);
        Pillar8 = (ImageView) findViewById(R.id.pillar8);
        Pillar9 = (ImageView) findViewById(R.id.pillar9);
        Pillar10 = (ImageView) findViewById(R.id.pillar10);
        //Pillar Tops
        PillarTop1 = (ImageView) findViewById(R.id.pillarTop1);
        PillarTop2 = (ImageView) findViewById(R.id.pillarTop2);
        PillarTop3 = (ImageView) findViewById(R.id.pillarTop3);
        PillarTop4 = (ImageView) findViewById(R.id.pillarTop4);
        PillarTop5 = (ImageView) findViewById(R.id.pillarTop5);
        PillarTop6 = (ImageView) findViewById(R.id.pillarTop6);
        //VISIBILTY
        Play_Button.setVisibility(View.VISIBLE);
        Logo.setVisibility(View.VISIBLE);

        Retry_Button.setVisibility(View.INVISIBLE);

        Score.setVisibility(View.INVISIBLE);
        ScoreOnBoard.setVisibility(View.INVISIBLE);
        HighscoreOnBoard.setVisibility(View.INVISIBLE);

        GameOver.setVisibility(View.INVISIBLE);
        Ball.setVisibility(View.INVISIBLE);
        ScoreBoard.setVisibility(View.INVISIBLE);

        Pillar1.setVisibility(View.INVISIBLE);        Pillar2.setVisibility(View.INVISIBLE);
        Pillar3.setVisibility(View.INVISIBLE);        Pillar4.setVisibility(View.INVISIBLE);
        Pillar5.setVisibility(View.INVISIBLE);        Pillar6.setVisibility(View.INVISIBLE);
        Pillar7.setVisibility(View.INVISIBLE);        Pillar8.setVisibility(View.INVISIBLE);
        Pillar9.setVisibility(View.INVISIBLE);        Pillar10.setVisibility(View.INVISIBLE);

        PillarTop1.setVisibility(View.INVISIBLE);        PillarTop2.setVisibility(View.INVISIBLE);
        PillarTop3.setVisibility(View.INVISIBLE);        PillarTop4.setVisibility(View.INVISIBLE);
        PillarTop5.setVisibility(View.INVISIBLE);        PillarTop6.setVisibility(View.INVISIBLE);
    }

    public static void sendViewToBack(final View child){
        final ViewGroup parent = (ViewGroup) child.getParent();
        if(null != parent){
           parent.removeView(child);
            parent.addView(child, 0);
        }
    }

    public boolean checkPillarPosition(float y){

        boolean low;
        if(y>900f){
            low=true;
        }else{
            low=false;
        }
        return low;
    }

    public void gameOver(){

        gameOverCalled = true;
        h.removeCallbacksAndMessages(null);

        sendViewToBack(Pillar1);        sendViewToBack(Pillar2);
        sendViewToBack(Pillar3);        sendViewToBack(Pillar4);
        sendViewToBack(Pillar5);        sendViewToBack(Pillar6);
        sendViewToBack(Pillar7);        sendViewToBack(Pillar8);
        sendViewToBack(Pillar9);        sendViewToBack(Pillar10);



        ScoreOnBoard.setText(String.valueOf(scoreValue));
        HighscoreOnBoard.setText(String.valueOf(highScoreValue));
        GameOver.setVisibility(View.VISIBLE);
        Retry_Button.setVisibility(View.VISIBLE);
        HighscoreOnBoard.setVisibility(View.VISIBLE);
        ScoreBoard.setVisibility(View.VISIBLE);
        ScoreOnBoard.setVisibility(View.VISIBLE);

        Ball.setVisibility(View.INVISIBLE);

        Pillar1.setVisibility(View.INVISIBLE);        Pillar2.setVisibility(View.INVISIBLE);
        Pillar3.setVisibility(View.INVISIBLE);        Pillar4.setVisibility(View.INVISIBLE);
        Pillar5.setVisibility(View.INVISIBLE);        Pillar6.setVisibility(View.INVISIBLE);
        Pillar7.setVisibility(View.INVISIBLE);        Pillar8.setVisibility(View.INVISIBLE);
        Pillar9.setVisibility(View.INVISIBLE);        Pillar10.setVisibility(View.INVISIBLE);

        PillarTop1.setVisibility(View.INVISIBLE);        PillarTop2.setVisibility(View.INVISIBLE);
        PillarTop3.setVisibility(View.INVISIBLE);        PillarTop4.setVisibility(View.INVISIBLE);
        PillarTop5.setVisibility(View.INVISIBLE);        PillarTop6.setVisibility(View.INVISIBLE);

        if(scoreValue > highScoreValue){
            highScoreValue = scoreValue;
            editor = pref.edit();
            editor.putInt("highScoreSaved",highScoreValue);
            editor.commit();
        }



        Retry_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onCreateNew();
                playBtnClicked();
                movement();

            }
        });

        sendViewToBack(Pillar1);    sendViewToBack(Pillar2);    sendViewToBack(Pillar3);
        sendViewToBack(Pillar4);    sendViewToBack(Pillar5);    sendViewToBack(Pillar6);
        sendViewToBack(Pillar7);    sendViewToBack(Pillar8);    sendViewToBack(Pillar9);    sendViewToBack(Pillar10);







    }

    protected void movement(){
        final int delay= 45;

        h.postDelayed(new Runnable(){
            public void run(){

                Layout.setOnTouchListener(new View.OnTouchListener() {
                    public boolean onTouch(View v, MotionEvent event) {


                        if (event.getAction() == MotionEvent.ACTION_UP) {
                            if (gameOverCalled == false) {
                                scoreValue++;
                                Score.setText(String.valueOf(scoreValue));
                                if (ballRight == true) {
                                    //Ball.setX(Ball.getX() + 6.7f);
                                    //Ball.setY(Ball.getY() - 0.5f);
                                    ballRight = false;
                                } else {
                                    //Ball.setX(Ball.getX() - 6.7f);
                                    //Ball.setY(Ball.getY() - 0.5f);
                                    ballRight = true;
                                }
                            } else {

                            }


                            return true;
                        }
                        return false;
                    }


                });

                if (ballRight == true){
                    Ball.setX(Ball.getX() + 6.7f);
                    Ball.setY(Ball.getY() - 0.5f);
                } else {
                    Ball.setX(Ball.getX() - 6.7f);
                    Ball.setY(Ball.getY() - 0.5f);
                }

                Pillar1.setY(Pillar1.getY()+5);                Pillar2.setY(Pillar2.getY()+5);
                Pillar3.setY(Pillar3.getY()+5);                Pillar4.setY(Pillar4.getY()+5);
                Pillar5.setY(Pillar5.getY()+5);                Pillar6.setY(Pillar6.getY()+5);
                Pillar7.setY(Pillar7.getY()+5);                Pillar8.setY(Pillar8.getY()+5);
                Pillar9.setY(Pillar9.getY()+5);                Pillar10.setY(Pillar10.getY()+5);

                Ball.setY(Ball.getY() + 0.5f);

                if(checkPillarPosition(Pillar1.getY())){
                    sendViewToBack(Pillar1);
                    Pillar1.setX(pillarPlacementX(Pillar10.getX()));
                    Pillar1.setY(pillarPlacementY(Pillar10.getY()));
                } else if(checkPillarPosition(Pillar2.getY())) {
                    sendViewToBack(Pillar2);
                    Pillar2.setX(pillarPlacementX(Pillar1.getX()));
                    Pillar2.setY(pillarPlacementY(Pillar1.getY()));
                } else if(checkPillarPosition(Pillar3.getY())) {
                    sendViewToBack(Pillar3);
                    Pillar3.setX(pillarPlacementX(Pillar2.getX()));
                    Pillar3.setY(pillarPlacementY(Pillar2.getY()));
                } else if(checkPillarPosition(Pillar4.getY())) {
                    sendViewToBack(Pillar4);
                    Pillar4.setX(pillarPlacementX(Pillar3.getX()));
                    Pillar4.setY(pillarPlacementY(Pillar3.getY()));
                } else if(checkPillarPosition(Pillar5.getY())) {
                    sendViewToBack(Pillar5);
                    Pillar5.setX(pillarPlacementX(Pillar4.getX()));
                    Pillar5.setY(pillarPlacementY(Pillar4.getY()));
                } else if(checkPillarPosition(Pillar6.getY())) {
                    sendViewToBack(Pillar6);
                    Pillar6.setX(pillarPlacementX(Pillar5.getX()));
                    Pillar6.setY(pillarPlacementY(Pillar5.getY()));
                } else if(checkPillarPosition(Pillar7.getY())) {
                    sendViewToBack(Pillar7);
                    Pillar7.setX(pillarPlacementX(Pillar6.getX()));
                    Pillar7.setY(pillarPlacementY(Pillar6.getY()));
                } else if(checkPillarPosition(Pillar8.getY())) {
                    sendViewToBack(Pillar8);
                    Pillar8.setX(pillarPlacementX(Pillar7.getX()));
                    Pillar8.setY(pillarPlacementY(Pillar7.getY()));
                } else if(checkPillarPosition(Pillar9.getY())) {
                    sendViewToBack(Pillar9);
                    Pillar9.setX(pillarPlacementX(Pillar8.getX()));
                    Pillar9.setY(pillarPlacementY(Pillar8.getY()));
                } else if(checkPillarPosition(Pillar10.getY())) {
                    sendViewToBack(Pillar10);
                    Pillar10.setX(pillarPlacementX(Pillar9.getX()));
                    Pillar10.setY(pillarPlacementY(Pillar9.getY()));
                }

                Ball.getHitRect(BallRect);
                Pillar1.getHitRect(Pillar1Rect);                Pillar2.getHitRect(Pillar2Rect);
                Pillar3.getHitRect(Pillar3Rect);                Pillar4.getHitRect(Pillar4Rect);
                Pillar5.getHitRect(Pillar5Rect);                Pillar6.getHitRect(Pillar6Rect);
                Pillar7.getHitRect(Pillar7Rect);                Pillar8.getHitRect(Pillar8Rect);
                Pillar9.getHitRect(Pillar9Rect);                Pillar10.getHitRect(Pillar10Rect);
                PillarTop1.getHitRect(PillarTop1Rect);                PillarTop2.getHitRect(PillarTop2Rect);
                PillarTop3.getHitRect(PillarTop3Rect);                PillarTop4.getHitRect(PillarTop4Rect);
                PillarTop5.getHitRect(PillarTop5Rect);                PillarTop6.getHitRect(PillarTop6Rect);

                if(Rect.intersects(BallRect, PillarTop1Rect) || Rect.intersects(BallRect, PillarTop2Rect) || Rect.intersects(BallRect, PillarTop3Rect) || Rect.intersects(BallRect, PillarTop4Rect) || Rect.intersects(BallRect, PillarTop5Rect) || Rect.intersects(BallRect, PillarTop6Rect) ){

                } else {
                    gameOver();
                }

                if(Rect.intersects(BallRect, Pillar1Rect)){
                    //SET VISIBLE WHEN YOU USE PILLARTOPS
                    PillarTop4.setVisibility(View.VISIBLE);
                    PillarTop5.setVisibility(View.VISIBLE);
                    PillarTop6.setVisibility(View.VISIBLE);

                    PillarTop1.setX(Pillar2.getX()+10.5f);
                    PillarTop1.setY(Pillar2.getY()+10.5f);
                    PillarTop2.setX(Pillar1.getX()+10.5f);
                    PillarTop2.setY(Pillar1.getY()+10.5f);
                    PillarTop3.setX(Pillar10.getX()+10.5f);
                    PillarTop3.setY(Pillar10.getY()+10.5f);
                    PillarTop4.setX(Pillar9.getX()+10.5f);
                    PillarTop4.setY(Pillar9.getY()+10.5f);
                    PillarTop5.setX(Pillar8.getX()+10.5f);
                    PillarTop5.setY(Pillar8.getY()+10.5f);
                    PillarTop6.setX(Pillar7.getX()+10.5f);
                    PillarTop6.setY(Pillar7.getY()+10.5f);
                } else if(Rect.intersects(BallRect, Pillar2Rect)){

                    PillarTop4.setVisibility(View.VISIBLE);
                    PillarTop5.setVisibility(View.VISIBLE);
                    PillarTop6.setVisibility(View.VISIBLE);

                    PillarTop1.setX(Pillar3.getX()+10.5f);
                    PillarTop1.setY(Pillar3.getY()+10.5f);
                    PillarTop2.setX(Pillar2.getX()+10.5f);
                    PillarTop2.setY(Pillar2.getY()+10.5f);
                    PillarTop3.setX(Pillar1.getX()+10.5f);
                    PillarTop3.setY(Pillar1.getY()+10.5f);
                    PillarTop4.setX(Pillar10.getX()+10.5f);
                    PillarTop4.setY(Pillar10.getY()+10.5f);
                    PillarTop5.setX(Pillar9.getX()+10.5f);
                    PillarTop5.setY(Pillar9.getY()+10.5f);
                    PillarTop6.setX(Pillar8.getX()+10.5f);
                    PillarTop6.setY(Pillar8.getY()+10.5f);
                } else if(Rect.intersects(BallRect, Pillar3Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar4.getX()+10.5f);
                    PillarTop1.setY(Pillar4.getY()+10.5f);
                    PillarTop2.setX(Pillar3.getX()+10.5f);
                    PillarTop2.setY(Pillar3.getY() + 10.5f);
                    PillarTop3.setX(Pillar2.getX() + 10.5f);
                    PillarTop3.setY(Pillar2.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar4Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar5.getX()+10.5f);
                    PillarTop1.setY(Pillar5.getY()+10.5f);
                    PillarTop2.setX(Pillar4.getX()+10.5f);
                    PillarTop2.setY(Pillar4.getY() + 10.5f);
                    PillarTop3.setX(Pillar3.getX() + 10.5f);
                    PillarTop3.setY(Pillar3.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar5Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar6.getX()+10.5f);
                    PillarTop1.setY(Pillar6.getY()+10.5f);
                    PillarTop2.setX(Pillar5.getX()+10.5f);
                    PillarTop2.setY(Pillar5.getY() + 10.5f);
                    PillarTop3.setX(Pillar4.getX() + 10.5f);
                    PillarTop3.setY(Pillar4.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar6Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar7.getX()+10.5f);
                    PillarTop1.setY(Pillar7.getY()+10.5f);
                    PillarTop2.setX(Pillar6.getX()+10.5f);
                    PillarTop2.setY(Pillar6.getY() + 10.5f);
                    PillarTop3.setX(Pillar5.getX() + 10.5f);
                    PillarTop3.setY(Pillar5.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar7Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar8.getX()+10.5f);
                    PillarTop1.setY(Pillar8.getY()+10.5f);
                    PillarTop2.setX(Pillar7.getX()+10.5f);
                    PillarTop2.setY(Pillar7.getY() + 10.5f);
                    PillarTop3.setX(Pillar6.getX() + 10.5f);
                    PillarTop3.setY(Pillar6.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar8Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar9.getX()+10.5f);
                    PillarTop1.setY(Pillar9.getY()+10.5f);
                    PillarTop2.setX(Pillar8.getX()+10.5f);
                    PillarTop2.setY(Pillar8.getY() + 10.5f);
                    PillarTop3.setX(Pillar7.getX() + 10.5f);
                    PillarTop3.setY(Pillar7.getY() + 10.5f);

                } else if(Rect.intersects(BallRect, Pillar9Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar10.getX()+10.5f);
                    PillarTop1.setY(Pillar10.getY()+10.5f);
                    PillarTop2.setX(Pillar9.getX()+10.5f);
                    PillarTop2.setY(Pillar9.getY()+10.5f);
                    PillarTop3.setX(Pillar8.getX()+10.5f);
                    PillarTop3.setY(Pillar8.getY()+10.5f);

                } else if(Rect.intersects(BallRect, Pillar10Rect)){

                    PillarTop4.setVisibility(View.INVISIBLE);
                    PillarTop5.setVisibility(View.INVISIBLE);
                    PillarTop6.setVisibility(View.INVISIBLE);

                    PillarTop1.setX(Pillar1.getX()+10.5f);
                    PillarTop1.setY(Pillar1.getY()+10.5f);
                    PillarTop2.setX(Pillar10.getX()+10.5f);
                    PillarTop2.setY(Pillar10.getY() + 10.5f);
                    PillarTop3.setX(Pillar9.getX() + 10.5f);
                    PillarTop3.setY(Pillar9.getY() + 10.5f);

                }

                h.postDelayed(this, delay);

            }

        }, delay);

    }

}
