package com.example.navigation_drawer_app.Clases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.navigation_drawer_app.Base.Base;

import java.util.ArrayList;

public class ConsolaPrecioDAO {

    private Base dbHelper;

    public ConsolaPrecioDAO (Context context)
    {
        dbHelper= new Base(context);
    }

    public long insertarConsolaPrecio(float precio, int njugadores, int id_consola)
    {
        SQLiteDatabase db =dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("precio", precio);
        values.put("cantidad_jugadores", njugadores);
        values.put("consola_id", id_consola);

        return db.insert ("consola_precio", null, values);
    }

    public ArrayList<String> verConsolaPrecio() {
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String sql =
                "SELECT cp.id, c.consola, cp.cantidad_jugadores, cp.precio " +
                        "FROM consola_precio cp " +
                        "INNER JOIN consola c ON c.id = cp.consola_id";

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String consola = cursor.getString(1);
                int jugadores = cursor.getInt(2);
                float precio = cursor.getFloat(3);

                lista.add(id + ";" + consola + ";" + jugadores + ";" + precio);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return lista;
    }


    public boolean eliminar(int id)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();

        int rows=db.delete("consola_precio",
                "id=?",
                new String[]{String.valueOf(id)}
        );

        return rows>0;
    }

    public boolean edit(int id, float precio, int njugadores)
    {
        SQLiteDatabase db=dbHelper.getWritableDatabase();
        ContentValues values= new ContentValues();

        values.put("precio", precio);
        values.put("cantidad_jugadores", njugadores);

        int rows=db.update("consola_precio",
                values,
                "id=?",
                new String[]{String.valueOf(id)});
        return rows>0;
    }
}
