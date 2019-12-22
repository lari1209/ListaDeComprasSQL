package com.example.alunos.listadecompras;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ListaOpenHelper extends SQLiteOpenHelper {

    public ListaOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE lista (" +
                "_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, " +
                "descricao TEXT NOT NULL," +
                "quantidade INTEGER" +
                ")";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO lista (descricao, quantidade) " +
                " VALUES ('Cebola', 5)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO lista (descricao, quantidade) " +
                " VALUES ('Sabonete', 3)";
        sqLiteDatabase.execSQL(sql);

        sql = "INSERT INTO lista (descricao, quantidade) " +
                " VALUES ('Tomate', 6)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
