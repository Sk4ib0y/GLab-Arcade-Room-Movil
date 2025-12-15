package com.example.navigation_drawer_app.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navigation_drawer_app.Base.Base;

public class UsuarioDAO
{
    private Base dbHelper;

    public UsuarioDAO(Context context) {
        dbHelper = new Base(context);
    }

    public long insertarUsuario(int clave, String nombre, String usuario, String contrasena)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues values= new ContentValues();

        values.put("clave",clave);
        values.put("nombre", nombre);
        values.put("usuario",usuario);
        values.put("contrasena",contrasena);

        return  db.insert("usuario",null,values);
    }

    public boolean login(String usuario, String contrasena)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();

        Cursor cursor=db.rawQuery(
                "SELECT id FROM usuario WHERE usuario=? AND contrasena=?",
                new String[]{usuario, contrasena}
        );
    boolean existe=cursor.moveToFirst();
    cursor.close();
    return existe;
    }
}
