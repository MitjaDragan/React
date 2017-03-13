package com.example.mitja.react;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;
import java.util.Random;

public class React extends AppCompatActivity {
    public int counter = 0;
    public int konec = 0;
    public int best = 10000;
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
        final Button clicker = (Button) findViewById(R.id.button);
        final Button druga = (Button) findViewById(R.id.button2);

        view = this.getWindow().getDecorView();
        view.setBackgroundResource(R.color.druga);
        druga.setVisibility(View.INVISIBLE);

        clicker.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(final View v) {
                clicker.setVisibility(View.INVISIBLE);
                counter = 0;
                final int num = rand.nextInt(60 - 35) + 35;
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
                        ada.setText("Press to start!");
                        clicker.setVisibility((View.VISIBLE));
                        view.setBackgroundResource(R.color.druga);
                        label.setText("Start React");
                        konec = counter - num;
                        label2.setText("Current time: " + String.valueOf(konec));
                        druga.setVisibility(View.INVISIBLE);
                        if (konec<=best){
                            label3.setText("Best time: " + konec);
                            best = konec;
                        }
                        else{
                            label3.setText("Best time: " + best);
                        }
                    }
                    public void onFinish2(){
                        ada.setText("Press to start!");
                        clicker.setVisibility((View.VISIBLE));
                        view.setBackgroundResource(R.color.prva);
                        label.setText("Start React");
                        label2.setText("TOO FAST BOIIIIIII");
                        druga.setVisibility(View.INVISIBLE);
                        if (konec<=best){
                            label3.setText("Best time: " + konec);
                            best = konec;
                        }
                        else{
                            label3.setText("Best time: " + best);
                        }
                    }
                }.start();
            }
        });
    }
}


