package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.utils.MyApplication;

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "eleicao.db";

    public DBHelper() {
        super(MyApplication.getContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MyApplication.getContext().getString(R.string.sql_create_table_candidato));
        db.execSQL(MyApplication.getContext().getString(R.string.sql_create_table_candidato_voto));
        db.execSQL(MyApplication.getContext().getString(R.string.sql_create_table_categoria_candidato));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // onUpgrade(db, oldVersion, newVersion);
    }
}
