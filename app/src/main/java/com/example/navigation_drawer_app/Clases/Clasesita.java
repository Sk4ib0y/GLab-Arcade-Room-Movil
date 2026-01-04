package com.example.navigation_drawer_app.Clases;

import android.widget.ImageView;

import com.example.navigation_drawer_app.R;

public class Clasesita
{
    public void selectImagen(String nombre, ImageView imagen)
    {
        if(nombre.contains("Xbox"))
        {
            imagen.setImageResource(R.drawable.xbox);
        }
        else if(nombre.equals("PS4") || nombre.equals("PS5"))
        {
            imagen.setImageResource(R.drawable.gaystation);
        }
        else if(nombre.contains("Nintendo"))
        {
            imagen.setImageResource(R.drawable.nintendo);
        }
        else if(nombre.contains("PC"))
        {
            imagen.setImageResource(R.drawable.pc);
        }
    }
}
