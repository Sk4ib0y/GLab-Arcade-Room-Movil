package com.example.navigation_drawer_app.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navigation_drawer_app.Base.Base;

import java.util.ArrayList;

public class ConsolaDAO
{

    private Base dbHelper;

    public ConsolaDAO(Context context)
    {
        dbHelper=new Base(context);
    }

    public long insertarConsola(String consola, String estado, String observaciones)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("consola", consola);
        contentValues.put("estado", estado);
        contentValues.put("observaciones",observaciones);

        return db.insert("consola", null, contentValues);
    }

    public boolean existe(String consola)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT id FROM consola WHERE consola=?",
                new String[]{consola}
                );
        boolean existe=cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    public ArrayList<String> verConsolas()
    {
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id, consola, estado, observaciones FROM consola", null);

        if(cursor.moveToFirst())
        {
            do {
                int id=cursor.getInt(cursor.getColumnIndexOrThrow("id"));
              String consola=cursor.getString(cursor.getColumnIndexOrThrow("Consola"));
              String estado=cursor.getString(cursor.getColumnIndexOrThrow("Estado"));
              String observaciones=cursor.getString(cursor.getColumnIndexOrThrow("Observaciones"));
              lista.add(id+";"+consola+";"+estado+";"+observaciones);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }

    public boolean eliminar(int id)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        int rows=db.delete("consola",
                "id=?",
                new String[]{String.valueOf(id)}
        );
        return rows>0;
    }

    public boolean edit(int id, String estado, String observaciones) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("estado", estado);
        values.put("observaciones", observaciones);

        int rows = db.update(
                "consola",
                values,
                "id=?",
                new String[]{String.valueOf(id)}
        );
        return rows > 0;
    }
}
