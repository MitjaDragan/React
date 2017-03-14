package com.example.mitja.react;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;

public class React extends AppCompatActivity {
    int counter = 0;
    int end = 0;
    int best = 10000;
    int currentBest = 10000;

    View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_react);

        final Random rand = new Random();
        final TextView label = (TextView) findViewById(R.id.textView);
        final TextView label2 = (TextView) findViewById(R.id.textView2);
        final TextView ada = (TextView) findViewById(R.id.textView3);
        final TextView label3 = (TextView) findViewById(R.id.textView4);
        final TextView label4 = (TextView) findViewById(R.id.textView5);
        final Button clicker = (Button) findViewById(R.id.button);
        final Button druga = (Button) findViewById(R.id.button2);


        Typeface myTypeface = Typeface.createFromAsset(getAssets(), "BEBAS___.ttf");
        label.setTypeface(myTypeface);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.druga);
        druga.setVisibility(View.INVISIBLE);

        SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
        best = preferences.getInt("best_time", 10000);
        if (best == 10000){
            label3.setVisibility(View.INVISIBLE);
        }

        label3.setText("All time best: " + Integer.toString(best));

        clicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                label.setTextColor(getResources().getColor(R.color.bluet));
                label2.setTextColor(getResources().getColor(R.color.bluen));
                label4.setTextColor(getResources().getColor(R.color.bluen));
                ada.setTextColor(getResources().getColor(R.color.bluen));
                label3.setTextColor(getResources().getColor(R.color.bluen));
                clicker.setVisibility(View.INVISIBLE);
                counter = 0;
                final int num = rand.nextInt(60 - 25) + 25;
                label.setVisibility(View.VISIBLE);
                label2.setText("Current time: 0");
                ada.setText("Press to start!");
                new CountDownTimer(10100000, 100) {
                    public void onTick(long millisecondsUntillFinished) {
                        ada.setText("Press to stop!");
                        label.setText(String.valueOf(counter));
                        druga.setVisibility(View.VISIBLE);
                        view.setBackgroundResource(R.color.druga);
                        if (counter >= num){
                            view.setBackgroundResource(R.color.zelena);
                            label.setVisibility(View.VISIBLE);

                            label.setTextColor(getResources().getColor(R.color.greent));
                        }
                        druga.setOnClickListener(new View.OnClickListener() {


                            @Override
                            public void onClick(View b) {
                                if (counter >= num) {
                                    cancel();
                                    onFinish();
                                }
                                if (counter < num) {
                                    cancel();
                                    onFinish2();
                                }
                            }
                        });
                        counter++;
                    }
                    public void onFinish() {
                        label.setTextColor(getResources().getColor(R.color.bluet));
                        ada.setText("Press to start!");
                        clicker.setVisibility((View.VISIBLE));
                        view.setBackgroundResource(R.color.druga);
                        label.setText("Start React");
                        end = counter - num;
                        label2.setText("Current time: " + String.valueOf(end));
                        druga.setVisibility(View.INVISIBLE);
                        if (end<=best){
                            label3.setText("All time best " + end);
                            best = end;
                            SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
                            preferences.edit().putInt("best_time", best).apply();
                            label3.setVisibility(View.VISIBLE);
                        }
                        else{
                            label3.setText("All time best: " + best);
                        }

                        if (end<=currentBest){
                            label4.setText("Current best: " + end);
                            currentBest = end;
                        }
                        else{
                            label4.setText("Current best: " + currentBest);
                        }

                    }
                    public void onFinish2(){
                        label.setTextColor(getResources().getColor(R.color.redt));
                        label2.setTextColor(getResources().getColor(R.color.redn));
                        label4.setTextColor(getResources().getColor(R.color.redn));
                        ada.setTextColor(getResources().getColor(R.color.redn));
                        label3.setTextColor(getResources().getColor(R.color.redn));
                        ada.setText("Press to start!");
                        clicker.setVisibility((View.VISIBLE));
                        view.setBackgroundResource(R.color.prva);
                        label.setText("Start React");
                        label2.setText("TOO FAST BOIIIIIII");
                        druga.setVisibility(View.INVISIBLE);
                        if (end<=best){
                            label3.setText("All time best: " + end);
                            best = end;
                            SharedPreferences preferences = getSharedPreferences("MyPreferences", 0);
                            preferences.edit().putInt("best_time", best).apply();
                        }
                        else{
                            label3.setText("All time best: " + best);
                        }
                    }
                }.start();
            }
        });
    }
}


