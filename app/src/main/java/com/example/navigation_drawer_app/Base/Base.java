package com.example.navigation_drawer_app.Base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Base extends SQLiteOpenHelper {

    private static final String DB_NAME = "g_lab_arcade.db";
    private static final int DB_VERSION = 2;

    private static final String CREATE_CONSOLA =
            "CREATE TABLE consola (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "consola TEXT NOT NULL," +
                    "estado TEXT NOT NULL," +
                    "observaciones TEXT" +
                    ");";

    private static final String CREATE_VIDEOJUEGO =
            "CREATE TABLE videojuego (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombre TEXT NOT NULL," +
                    "imagen TEXT," +
                    "genero TEXT," +
                    "plataforma TEXT" +
                    ");";

    private static final String CREATE_CONSOLA_PRECIO =
            "CREATE TABLE consola_precio (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "precio REAL NOT NULL," +
                    "cantidad_jugadores INTEGER," +
                    "consola_id INTEGER NOT NULL," +
                    "FOREIGN KEY (consola_id) REFERENCES consola(id)" +
                    ");";


    private static final String CREATE_USUARIO =
            "CREATE TABLE usuario (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "clave INTEGER NOT NULL,"+
                    "nombre TEXT NOT NULL,"+
                    "usuario TEXT NOT NULL UNIQUE," +
                    "contrasena TEXT NOT NULL" +
                    ");";

    private static final String CREATE_MENU =
            "CREATE TABLE menu (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "elemento TEXT NOT NULL UNIQUE," +
                    "precio REAL NOT NULL" +
                    ");";


    public Base(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CONSOLA);
        db.execSQL(CREATE_VIDEOJUEGO);
        db.execSQL(CREATE_USUARIO);
        db.execSQL(CREATE_MENU);
        db.execSQL(CREATE_CONSOLA_PRECIO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS consola_precio");
        db.execSQL("DROP TABLE IF EXISTS videojuego");
        db.execSQL("DROP TABLE IF EXISTS consola");
        db.execSQL("DROP TABLE IF EXISTS usuario");
        db.execSQL("DROP TABLE IF EXISTS menu");
        onCreate(db);
    }
}