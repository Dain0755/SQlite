package com.example.sqldefinitivo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

import androidx.annotation.Nullable;

public class BaseDeDatos extends SQLiteOpenHelper {
    public BaseDeDatos(@Nullable Context onClickListener, @Nullable String nombres, @Nullable SQLiteDatabase.CursorFactory factory, int version){
        super(onClickListener, nombres, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase baseDatos){
        baseDatos.execSQL("CREATE TABLE usuario(identify int primary key, nombre text, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){

    }

}
