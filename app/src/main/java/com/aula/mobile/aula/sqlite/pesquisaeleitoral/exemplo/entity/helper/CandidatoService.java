package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CandidatoService {

//    public static final String TABLE_CANDIDATO = MyApplication.getContext().getString(R.string.tbl_candidato);
    private DBHelper dBHelper;

    public CandidatoService() {
        dBHelper = new DBHelper();
    }

    public List<Candidato> getCandidatos() {
        return lerListaCandidatos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato;", null));
    }

    public List<Candidato> getCandidatosPorCategoria(int idCategoria) {
        return lerListaCandidatos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato WHERE idCategoria = ? ORDER BY nome ASC;", new String[]{String.valueOf(idCategoria)}));
    }

    // Métodos auxiliares

    private List<Candidato> lerListaCandidatos(Cursor cursor) {
        List<Candidato> listaCandidatos = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String partido = cursor.getString(cursor.getColumnIndex("partido"));
            Categoria categoria = CategoriaService.getCategoriaById(cursor.getInt(cursor.getColumnIndex("idCategoria")));

            listaCandidatos.add(new Candidato(id, nome, partido, categoria));
        }

        return listaCandidatos;
    }

    private Candidato lerCandidato(Cursor cursor) {
        Candidato candidato = null;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String partido = cursor.getString(cursor.getColumnIndex("partido"));
            Categoria categoria = CategoriaService.getCategoriaById(cursor.getInt(cursor.getColumnIndex("idCategoria")));

            candidato = new Candidato(id, nome, partido, categoria);
        }

        return candidato;
    }

//    public void add() {
//        insert("Jair Bolsonaro", "PSL", ECategoria.PRESIDENTE.getId());
//        insert("Fernando Haddad", "PT", ECategoria.PRESIDENTE.getId());
//        insert("Ciro Gomes", "PDT", ECategoria.PRESIDENTE.getId());
//        insert("Geraldo Alckmin", "PSDB", ECategoria.PRESIDENTE.getId());
//        //
//        insert("Ratinho JR", "PSD", ECategoria.GOVERNADOR_PR.getId());
//        insert("Cida", "PP", ECategoria.GOVERNADOR_PR.getId());
//        insert("João Arruda", "MDB", ECategoria.GOVERNADOR_PR.getId());
//
//        insert("Dória (viadinho com areia no cu)", "PSDB", ECategoria.GOVERNADOR_SP.getId());
//    }
//
//    private void insert(String nome, String partido, int idCategoria) {
//        ContentValues values = new ContentValues();
//        values.put("nome", nome);
//        values.put("partido", partido);
//        values.put("idCategoria", idCategoria);
//        dBHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
//    }
//
//    public int count() {
//        Cursor mCount = dBHelper.getReadableDatabase()
//                .rawQuery("select count(*) from " + TABLE_CANDIDATO, null);
//        mCount.moveToFirst();
//        int count = mCount.getInt(0);
//        mCount.close();
//        return count;
//    }
}
