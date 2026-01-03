package com.example.navigation_drawer_app.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navigation_drawer_app.Base.Base;

import java.util.ArrayList;

public class MenuDAO
{
    private Base dbHelper;

    public MenuDAO(Context context) {
        dbHelper = new Base(context);
    }

    public long insertarProducto(String elemento, float precio)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("elemento", elemento);
        values.put("precio", precio);

        return db.insert("menu", null, values);
    }

    public boolean existe(String elemento, float precio)
    {
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor= db.rawQuery(
                "SELECT id FROM menu WHERE elemento=? AND precio=?",
                new String[]{elemento, String.valueOf(precio)}
        );
        boolean existe=cursor.moveToFirst();
        cursor.close();
        return existe;
    }

    public ArrayList<String> verProductos()
    {
        ArrayList<String> lista= new ArrayList<>();
        SQLiteDatabase db=dbHelper.getReadableDatabase();
        Cursor cursor=db.rawQuery(
                "SELECT * FROM menu", null
        );

        if (cursor.moveToFirst())
        {
            do {
                String elemento= cursor.getString(cursor.getColumnIndexOrThrow("elemento"));
                float precio=cursor.getFloat(cursor.getColumnIndexOrThrow("precio"));
                lista.add(elemento+";"+precio);
            }
            while (cursor.moveToNext());
        }
        cursor.close();
        return lista;
    }


    public boolean eliminar(String elemento, float precio)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        int rows= db.delete("menu",
                "elemento=? AND precio=?",
                new String[]{elemento, String.valueOf(precio)}
                );
        return rows>0;
    }

    public boolean edit(String elementoViejo, String elementoNuevo, float precioNuevo)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        ContentValues contentValues= new ContentValues();

        contentValues.put("elemento", elementoNuevo);
        contentValues.put("precio", precioNuevo);

        int rows=db.update("menu",
                contentValues,
                "elemento=?",
                new String[]{elementoViejo}
        );

        return rows>0;
    }


}
