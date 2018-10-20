package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.content.ContentValues;
import android.database.Cursor;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.utils.MyApplication;

import java.util.ArrayList;
import java.util.List;

public class CandidatoService {

    public static final String TABLE_CANDIDATO = MyApplication.getContext().getString(R.string.tbl_candidato);
    private DBHelper dBHelper;

    public CandidatoService() {
        dBHelper = new DBHelper();
    }

    public List<Candidato> buscarCandidatos() {
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
            Categoria categoria = CategoriaService.buscarCategoriaPorId(cursor.getInt(cursor.getColumnIndex("idCategoria")));

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
            Categoria categoria = CategoriaService.buscarCategoriaPorId(cursor.getInt(cursor.getColumnIndex("idCategoria")));

            candidato = new Candidato(id, nome, partido, categoria);
        }

        return candidato;
    }

    // Métodos para preparar o banco de dados

    public void adicionarCandidatos() {
        List<Candidato> listaCandidatos = new ArrayList<>();

        Categoria categoriaPresidente = CategoriaService.buscarCategoriaPorDescricao("PRESIDENTE");
        Categoria categoriaGovernador = CategoriaService.buscarCategoriaPorDescricao("GOVERNADOR");
        Categoria categoriaSenador = CategoriaService.buscarCategoriaPorDescricao("SENADOR");

        // Presidentes
        listaCandidatos.add(new Candidato(17, "Jair Bolsonaro", "PSL", categoriaPresidente));
        listaCandidatos.add(new Candidato(13, "Fernando Haddad", "PT", categoriaPresidente));
        listaCandidatos.add(new Candidato(12, "Ciro Gomes", "PDT", categoriaPresidente));
        listaCandidatos.add(new Candidato(45, "Geraldo Alckmin", "PSDB", categoriaPresidente));
        listaCandidatos.add(new Candidato(30, "João Amoedo", "NOVO", categoriaPresidente));

        // Governadores
        listaCandidatos.add(new Candidato(55, "Ratinho JR", "PSD", categoriaGovernador));
        listaCandidatos.add(new Candidato(11, "Cida Borghetti", "PP", categoriaGovernador));
        listaCandidatos.add(new Candidato(15, "João Arruda", "MDB", categoriaGovernador));
        listaCandidatos.add(new Candidato(30, "Romeu Zema", "NOVO", categoriaGovernador));

        // Senadores
        listaCandidatos.add(new Candidato(181, "Flávio Arns", "REDE", categoriaSenador));
        listaCandidatos.add(new Candidato(191, "Prof. Oriovisto Guimarães", "PODE", categoriaSenador));
        listaCandidatos.add(new Candidato(457, "Mara Gabrilli", "PSDB", categoriaSenador));
        listaCandidatos.add(new Candidato(177, "Major Olímpio", "PSL", categoriaSenador));

        salvarCandidatos(listaCandidatos);
    }

    private void salvarCandidatos(List<Candidato> listaCandidatos) {
        for (Candidato candidato : listaCandidatos) {
            ContentValues values = new ContentValues();
            values.put("id", candidato.getId());
            values.put("nome", candidato.getNome());
            values.put("partido", candidato.getPartido());
            values.put("idCategoria", candidato.getCategoria().getId());
            dBHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
        }
    }

}
