package com.example.navigation_drawer_app.Actividades;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.navigation_drawer_app.R;

public class NoDisponible extends AppCompatActivity implements View.OnClickListener {


    Button volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_no_disponible);

        volver=findViewById(R.id.volver);
        volver.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == volver.getId())
        {
            finish();
        }
    }
}