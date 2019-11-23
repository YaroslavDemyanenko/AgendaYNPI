package com.example.agendaynpi.Actividades;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.agendaynpi.R;

public class MainActivity extends AppCompatActivity {

    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences preferencias=getSharedPreferences("login", Context.MODE_PRIVATE);
        TextView saludo= findViewById(R.id.lblSaludoPrincipal);
        saludo.setText(this.getResources().getString(R.string.saludo,preferencias.getString("usuario", "root")));
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
                if (y1 > y2) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
        return false;
    }

    //Esto desactiva el boton de volver
    @Override
    public void onBackPressed() {

    }
}
