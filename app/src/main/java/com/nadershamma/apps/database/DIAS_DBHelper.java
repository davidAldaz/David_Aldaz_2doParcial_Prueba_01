package com.nadershamma.apps.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DIAS_DBHelper extends SQLiteOpenHelper {
    public DIAS_DBHelper(Context context, String name,
                         SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /*Creacion de la tabla con 3 campos un integer que es auto incrementable
         * un texto que no puede ser nulo que es el nombre del usuario
         * la contraseña que tambien es un texto y no nulo*/
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "username text NOT NULL,clave_user text NOT NULL)");
        /*Hacemos un insert para tener un valkor insertado como predeterminado*/
        db.execSQL("insert into userstable(username,clave_user) values('admin','admin')");
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        /*Creacion de la tabla con 3 campos un integer que es auto incrementable
         * un texto que no puede ser nulo que es el nombre del usuario
         * la contraseña que tambien es un texto y no nulo*/
        db.execSQL("create table userstable(id_user integer  PRIMARY KEY AUTOINCREMENT NOT NULL," +
                "username text NOT NULL,clave_user text NOT NULL)");
        /*Hacemos un insert para tener un valkor insertado como predeterminado*/
        db.execSQL("insert into userstable(username,clave_user) values('admin','admin')");
    }}
