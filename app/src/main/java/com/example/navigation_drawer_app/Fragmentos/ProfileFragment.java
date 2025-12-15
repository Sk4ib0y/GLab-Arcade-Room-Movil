package com.example.navigation_drawer_app.Fragmentos;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.navigation_drawer_app.R;
import com.example.navigation_drawer_app.Clases.SesionManager;
import com.example.navigation_drawer_app.Clases.UsuarioDAO;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    LinearLayout login, registro, sesion;
    EditText nombre, clave, contralogin, userlogin, contraregistro, userregistro, confircontra;
    TextView logintext, registrotext, cerrarsesion,usersesion;
    Button registrob, loginb;
    UsuarioDAO usuarioDAO;

    SesionManager sesionManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        login=view.findViewById(R.id.login);
        registro=view.findViewById(R.id.registro);
        sesion=view.findViewById(R.id.sesioniniciada);

        nombre=view.findViewById(R.id.nombre);
        clave=view.findViewById(R.id.clave);
        contralogin=view.findViewById(R.id.contraseña);
        userlogin=view.findViewById(R.id.user);
        contraregistro=view.findViewById(R.id.contraseñaregistro);
        userregistro=view.findViewById(R.id.userregistro);
        confircontra=view.findViewById(R.id.confircontraseña);


        registrotext=view.findViewById(R.id.registrotext);
        logintext=view.findViewById(R.id.logintext);
        cerrarsesion=view.findViewById(R.id.cerrarsesion);
        usersesion=view.findViewById(R.id.UsuarioSesion);

        registrob=view.findViewById(R.id.registrob);
        loginb=view.findViewById(R.id.loginb);

        logintext.setOnClickListener(this);
        registrotext.setOnClickListener(this);
        cerrarsesion.setOnClickListener(this);
        registrob.setOnClickListener(this);
        loginb.setOnClickListener(this);

        usuarioDAO=new UsuarioDAO(requireContext());
        sesionManager=new SesionManager(requireContext());
        actualizarUI();
        return view;
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();

        if(id==logintext.getId())
        {
            login.setVisibility(View.GONE);
            registro.setVisibility(View.VISIBLE);
        }
        else if(registrotext.getId()==id)
        {
            login.setVisibility(View.VISIBLE);
            registro.setVisibility(View.GONE);
        }


        else if(registrob.getId()==id)
        {
            String nom=nombre.getText().toString().trim();
            String txtmatricula=clave.getText().toString().trim();
            String user=userregistro.getText().toString().trim();
            String pass=contraregistro.getText().toString().trim();
            String confirm=confircontra.getText().toString().trim();

            if(nom.isEmpty() || txtmatricula.isEmpty() || user.isEmpty() || pass.isEmpty() || confirm.isEmpty())
            {
                Toast.makeText(requireContext(), "Debes rellenar todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!pass.equals(confirm))
            {
                Toast.makeText(requireContext(), "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                return;
            }

            int matricula;
                try {
                    matricula=Integer.parseInt(txtmatricula);
                } catch (NumberFormatException e)
                {
                    Toast.makeText(requireContext(), "La matricula debe ser numerica", Toast.LENGTH_SHORT).show();
                    return;
                }
            long resultado= usuarioDAO.insertarUsuario(matricula,nom,user,pass);

            if(resultado!=-1)
            {
                Toast.makeText(requireContext(), "¡Usuario registrado correctamente!", Toast.LENGTH_SHORT).show();
                sesionManager.login(user);
                actualizarUI();
            }
            else
            {
                Toast.makeText(requireContext(), "Usuario existente", Toast.LENGTH_SHORT).show();
            }
        }

        else if(loginb.getId()==id)
        {
            String user=userlogin.getText().toString();
            String pass=contralogin.getText().toString();

            if(usuarioDAO.login(user,pass))
            {
                sesionManager.login(user);
                actualizarUI();
            }
            else
            {
                Toast.makeText(requireContext(), "El usuario o contraseña son incorrectos", Toast.LENGTH_SHORT).show();
            }
        }

        else if(cerrarsesion.getId()==id)
        {
            sesion.setVisibility(View.GONE);
            login.setVisibility(View.VISIBLE);
            sesionManager.logout();
            actualizarUI();
        }

    }
    private void actualizarUI()
    {
        boolean loginb=sesionManager.isAdmin();

        if(loginb)
        {
            usersesion.setText("¡Hola! "+sesionManager.getUser());
            login.setVisibility(View.GONE);
            registro.setVisibility(View.GONE);
            sesion.setVisibility(View.VISIBLE);
        }
        else
        {
            login.setVisibility(View.VISIBLE);
            registro.setVisibility(View.GONE);
            sesion.setVisibility(View.GONE);
        }
    }
}