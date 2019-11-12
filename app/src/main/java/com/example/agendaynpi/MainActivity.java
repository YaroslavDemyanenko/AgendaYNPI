package com.example.agendaynpi;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    float x1,x2,y1,y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onTouchEvent(MotionEvent eventoTouch) {

        switch (eventoTouch.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = eventoTouch.getX();
                y1 = eventoTouch.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = eventoTouch.getX();
                y2 = eventoTouch.getY();
                if(y1>y2){
                    Intent intent = new Intent(MainActivity.this,Login.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }
}
