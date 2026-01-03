package com.example.navigation_drawer_app.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navigation_drawer_app.Base.Base;

import java.util.ArrayList;

public class VideojuegoDAO
{
    private Base dbHelper;

    public VideojuegoDAO(Context context)
    {
        dbHelper= new Base(context);
    }

    public long insertarVideojuego(String nombre, String genero, String plataforma)
    {
        SQLiteDatabase db= dbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("genero", genero);
        contentValues.put("plataforma", plataforma);

        return db.insert("videojuego", null, contentValues);
    }

    public ArrayList<String>verVideojuegos()
    {
        ArrayList<String>lista=new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id, nombre, genero, plataforma FROM videojuego", null);

        if(cursor.moveToFirst())
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
                String nombre=cursor.getString(cursor.getColumnIndexOrThrow("nombre"));
                String genero=cursor.getString(cursor.getColumnIndexOrThrow("genero"));
                String plataforma=cursor.getString(cursor.getColumnIndexOrThrow("plataforma"));
                lista.add(id+";"+nombre+";"+genero+";"+plataforma);

            }while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public boolean eliminar(int id)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        int rows=db.delete("videojuego", "id=?",
                new String[]{String.valueOf(id)}
        );
        return rows>0;
    }

    public boolean edit(int id, String nombre, String genero, String plataforma)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues= new ContentValues();

        contentValues.put("nombre", nombre);
        contentValues.put("genero", genero);
        contentValues.put("plataforma", plataforma);

        int rows=db.update("videojuego",
                contentValues,
                "id=?",
                new String[]{String.valueOf(id)});

        return rows>0;
    }
}
