package com.example.stuart.braintrainerapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    CountDownTimer countDownTimer;
    Button one;
    Button two;
    Button three;
    Button four;
    Button goButton;
    Button playAgainButton;
    TextView cuurentTime;
    TextView score;
    TextView question;
    TextView result;
    final int min = 0;
    final int max = 1000;
    final int solMin = 0;
    final int solMax = 2000; // minimum to maximum possible solution answers
    final int assignMin = 0;
    final int assignMax = 3; // minimum to maximum possible solution answers
    int answer;
    int pointsScored = 0;
    int noQuestions = 0;
    int counter = 4;
    boolean complete = false;
    int[] buttonDisplays = new int[]{1,1,1,1};

    public void assignButtonOptions()
    {
        while(counter !=0)
        {
            Log.i("startCounter", ""+counter);
            final int option = new Random().nextInt((assignMax - assignMin) + 1) + assignMin; //Determines the button option

            if( buttonDisplays[option] != 1 ) // already selected
            {
                Log.i("startCounter", "button "+option + " already has a value assigned to it");
                continue;
            }

            else
            {

                switch (option)
                {
                    case 0:

                        buttonDisplays[option]--;

                        if(counter == 4)
                        {
                            one.setTag("answer");
                            one.setText(""+answer);
                            Log.i("oneAnswer", ""+answer);
                        }
                        else
                        {

                            while(complete == false)
                            {
                                final int randomDisplay = new Random().nextInt((solMax - solMin) + 1) + solMin;
                                if( randomDisplay == answer)
                                {
                                    continue;
                                }
                                else
                                {
                                    one.setTag("wrong");
                                    one.setText(""+randomDisplay);
                                    complete = true;
                                    Log.i("oneRandom", ""+randomDisplay);
                                }
                            }

                        }

                        break;


                    case 1:

                        buttonDisplays[option]--;

                        if(counter == 4)
                        {
                            two.setTag("answer");
                            two.setText(""+answer);
                            Log.i("twoAnswer", ""+answer);
                        }
                        else
                        {

                            while(complete == false)
                            {
                                final int randomDisplay = new Random().nextInt((solMax - solMin) + 1) + solMin;
                                if( randomDisplay == answer)
                                {
                                    continue;
                                }
                                else
                                {
                                    two.setTag("wrong");
                                    two.setText(""+randomDisplay);
                                    complete = true;
                                    Log.i("twoRandom", ""+randomDisplay);
                                }
                            }
                        }

                        break;


                    case 2:

                        buttonDisplays[option]--;

                        if(counter == 4)
                        {
                            three.setTag("answer");
                            three.setText(""+answer);
                            Log.i("thirdAnswer", ""+answer);

                        }
                        else
                        {

                            while(complete == false)
                            {
                                final int randomDisplay = new Random().nextInt((solMax - solMin) + 1) + solMin;
                                if( randomDisplay == answer)
                                {
                                    continue;
                                }
                                else
                                {
                                    three.setTag("wrong");
                                    three.setText(""+randomDisplay);
                                    complete = true;
                                    Log.i("thirdRandom", ""+randomDisplay);
                                }
                            }
                        }

                        break;


                    case 3:

                        buttonDisplays[option]--;

                        if(counter == 4)
                        {
                            four.setTag("answer");
                            four.setText(""+answer);
                            Log.i("fourthAnswer", ""+answer);
                        }
                        else
                        {

                            while(complete == false)
                            {
                                final int randomDisplay = new Random().nextInt((solMax - solMin) + 1) + solMin;
                                if( randomDisplay == answer)
                                {
                                    continue;
                                }
                                else
                                {
                                    four.setTag("wrong");
                                    four.setText(""+randomDisplay);
                                    complete = true;
                                    Log.i("fourthRandom", ""+randomDisplay);
                                }
                            }

                        }

                        break;

                }
                counter--;
                complete = false;

            }

            Log.i("endCounter", ""+counter);

        }

       // Reset Button Display Options and Counter
        for(int i=0; i<4; i++)
        {
            buttonDisplays[i] = 1;
        }
        counter =4;

    }


    public void newQuestion() //generates new question
    {

        final int firstRandom = new Random().nextInt((max - min) + 1) + min;
        final int secondRandom = new Random().nextInt((max - min) + 1) + min;
        answer = firstRandom + secondRandom;

        //assigning the options randomly

        assignButtonOptions();

        question.setText(""+firstRandom + " + " +secondRandom);

    }


    public void resetTimer()
    {
        cuurentTime.setText("0:30");
        countDownTimer.cancel();
        goButton.setText("GO!");
    }

    public void updateTimer(int secondsLeft)
    {

        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes *60;


        String secondString = Integer.toString(seconds);

        if(seconds <=9)
        {
            secondString = "0" + secondString;
        }


        cuurentTime.setText(secondString + "s");
    }

    public void go(View view)
    {
        pointsScored =0;
        noQuestions =0;
        goButton.setVisibility(View.INVISIBLE);
        playAgainButton.setVisibility(View.INVISIBLE);
        one.setVisibility(View.VISIBLE);
        two.setVisibility(View.VISIBLE);
        three.setVisibility(View.VISIBLE);
        four.setVisibility(View.VISIBLE);
        cuurentTime.setVisibility(View.VISIBLE);
        score.setVisibility(View.VISIBLE);
        question.setVisibility(View.VISIBLE);
        result.setVisibility(View.VISIBLE);
        result.setText("");

        one.setEnabled(true);
        two.setEnabled(true);
        three.setEnabled(true);
        four.setEnabled(true);

        score.setText(pointsScored + "/" +noQuestions);
        newQuestion();

            countDownTimer = new CountDownTimer(30000 + 100, 1000) {
                public void onTick(long millisecondsUntilDone) {

                    //Countdown is counting down (every second)
                    Log.i("Seconds left", String.valueOf(millisecondsUntilDone / 1000));
                    updateTimer((int) millisecondsUntilDone / 1000);


                }

                public void onFinish() {
                    resetTimer();
                    Log.i("Done!", "Countdown Timer Finished");
                    result.setText("Your Score is " +pointsScored + "/" +noQuestions);
                    resetTimer();


                    playAgainButton.setVisibility(View.VISIBLE);
                    one.setEnabled(false);
                    two.setEnabled(false);
                    three.setEnabled(false);
                    four.setEnabled(false);


                }

            }.start();
    }


    public void click(View view)
    {


        noQuestions++;


        String tag = view.getTag().toString();

        if(tag.equals("answer"))
        {
            pointsScored++;
            result.setText("CORRECT!");
        }
        else
        {
            result.setText("WRONG!");
        }

        newQuestion();
        score.setText(pointsScored + "/" +noQuestions);


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);
        goButton = (Button) findViewById(R.id.goButton);
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);

        cuurentTime = (TextView) findViewById(R.id.timer);
        score = (TextView) findViewById(R.id.score);
        question = (TextView) findViewById(R.id.question);
        result = (TextView) findViewById(R.id.result);


        one.setVisibility(View.INVISIBLE);
        two.setVisibility(View.INVISIBLE);
        three.setVisibility(View.INVISIBLE);
        four.setVisibility(View.INVISIBLE);
        cuurentTime.setVisibility(View.INVISIBLE);
        score.setVisibility(View.INVISIBLE);
        question.setVisibility(View.INVISIBLE);
        result.setVisibility(View.INVISIBLE);

    }
}
