package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class CandidatoHelper {

    private DbHelper dbHelper;
    public static final String TABLE_CANDIDATO = "candidato";

    public CandidatoHelper(Context context) {
        dbHelper = new DbHelper(context);
    }

    public List<Candidato> getList(int idCategoria) {
        List<Candidato> list = new ArrayList<>(0);
        Cursor cursor = dbHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato WHERE idCategoria = " + idCategoria + " ORDER BY nome ASC", null);
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                int ID = cursor.getInt(cursor.getColumnIndex("id"));
                String nome = cursor.getString(cursor.getColumnIndex("nome"));
                String partido = cursor.getString(cursor.getColumnIndex("partido"));
                list.add(new Candidato(ID, nome, partido));
                cursor.moveToNext();
            }
        }
        return list;
    }

    public void add() {
        insert("Jair Bolsonaro", "PSL", ECategoria.PRESIDENTE.getId());
        insert("Fernando Haddad", "PT", ECategoria.PRESIDENTE.getId());
        insert("Ciro Gomes", "PDT", ECategoria.PRESIDENTE.getId());
        insert("Geraldo Alckmin", "PSBD", ECategoria.PRESIDENTE.getId());
        //
        insert("Ratinho JR", "PSD", ECategoria.GOVERNADOR_PR.getId());
        insert("Cida", "PP", ECategoria.GOVERNADOR_PR.getId());
        insert("João Arruda", "MDB", ECategoria.GOVERNADOR_PR.getId());
    }

    private void insert(String nome, String partido, int idCategoria) {
        ContentValues values = new ContentValues();
        values.put("nome", nome);
        values.put("partido", partido);
        values.put("idCategoria", idCategoria);
        dbHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
    }

    public int count() {
        Cursor mCount = dbHelper.getReadableDatabase()
                .rawQuery("select count(*) from " + TABLE_CANDIDATO, null);
        mCount.moveToFirst();
        int count = mCount.getInt(0);
        mCount.close();
        return count;
    }
}
