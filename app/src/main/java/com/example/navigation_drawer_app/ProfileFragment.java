package com.example.navigation_drawer_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    LinearLayout login, registro;
    EditText nombre, clave, contralogin, userlogin, contraregistro, userregistro, confircontra;
    TextView logintext, registrotext;
    Button registrob, loginb;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        login=view.findViewById(R.id.login);
        registro=view.findViewById(R.id.registro);

        nombre=view.findViewById(R.id.nombre);
        clave=view.findViewById(R.id.clave);
        contralogin=view.findViewById(R.id.contraseña);
        userlogin=view.findViewById(R.id.user);
        contraregistro=view.findViewById(R.id.contraseñaregistro);
        userregistro=view.findViewById(R.id.userregistro);
        confircontra=view.findViewById(R.id.confircontraseña);


        registrotext=view.findViewById(R.id.registrotext);
        logintext=view.findViewById(R.id.logintext);

        registrob=view.findViewById(R.id.registrob);
        loginb=view.findViewById(R.id.loginb);

        logintext.setOnClickListener(this);
        registrotext.setOnClickListener(this);
        registrob.setOnClickListener(this);
        loginb.setOnClickListener(this);



        return view;
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();

        if(id==logintext.getId())
        {
            login.setVisibility(View.INVISIBLE);
            registro.setVisibility(View.VISIBLE);
        }
        else if(registrotext.getId()==id)
        {
            login.setVisibility(View.VISIBLE);
            registro.setVisibility(View.INVISIBLE);
        }
        else if(registrob.getId()==id)
        {
            Intent intent= new Intent(getActivity(), NoDisponible.class);
            startActivity(intent);
        }
        else if(loginb.getId()==id)
        {
            Intent intent= new Intent(getActivity(), NoDisponible.class);
            startActivity(intent);
        }
    }
}