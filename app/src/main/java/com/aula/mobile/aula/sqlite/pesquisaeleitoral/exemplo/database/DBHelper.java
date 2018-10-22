package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aula.mobile.aula.R;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "eleicao.database." + Math.random();
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(context.getString(R.string.sql_create_table_candidato));
        db.execSQL(context.getString(R.string.sql_create_table_candidato_voto));
        db.execSQL(context.getString(R.string.sql_create_table_categoria_candidato));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade(db, oldVersion, newVersion);
    }
}
