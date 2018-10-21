package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CandidatoService {

    public final String TABLE_CANDIDATO = "candidato";
    private Context context;
    private DBHelper dBHelper;

    public CandidatoService(Context context) {
        this.context = context;
        dBHelper = new DBHelper(context);
    }

    public List<Candidato> buscarCandidatos() {
        return lerListaCandidatos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato;", null));
    }

    public List<Candidato> buscarCandidatosPorCategoria(int idCategoria) {
        return lerListaCandidatos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato WHERE id_categoria = ? ORDER BY nome ASC;", new String[]{String.valueOf(idCategoria)}));
    }

    public Candidato buscarCandidatoPorId(int idCandidato) {
        return lerCandidato(dBHelper.getReadableDatabase().rawQuery("SELECT * FROM candidato WHERE id = ?", new String[]{String.valueOf(idCandidato)}));
    }

    // Métodos auxiliares

    private List<Candidato> lerListaCandidatos(Cursor cursor) {
        CategoriaService categoriaService = new CategoriaService(context);
        List<Candidato> listaCandidatos = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String foto = cursor.getString(cursor.getColumnIndex("foto"));
            String partido = cursor.getString(cursor.getColumnIndex("partido"));
            Categoria categoria = categoriaService.buscarCategoriaPorId(cursor.getInt(cursor.getColumnIndex("id_categoria")));

            listaCandidatos.add(new Candidato(id, nome, foto, partido, categoria));
        }

        return listaCandidatos;
    }

    private Candidato lerCandidato(Cursor cursor) {
        CategoriaService categoriaService = new CategoriaService(context);
        Candidato candidato = null;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String nome = cursor.getString(cursor.getColumnIndex("nome"));
            String foto = cursor.getString(cursor.getColumnIndex("foto"));
            String partido = cursor.getString(cursor.getColumnIndex("partido"));
            Categoria categoria = categoriaService.buscarCategoriaPorId(cursor.getInt(cursor.getColumnIndex("id_categoria")));

            candidato = new Candidato(id, nome, foto, partido, categoria);
        }

        return candidato;
    }

    // Métodos para preparar o banco de dados

    public void adicionarCandidatos() {
        CategoriaService categoriaService = new CategoriaService(context);
        List<Candidato> listaCandidatos = new ArrayList<>();

        Categoria categoriaPresidente = categoriaService.buscarCategoriaPorDescricao("PRESIDENTE");
        Categoria categoriaGovernador = categoriaService.buscarCategoriaPorDescricao("GOVERNADOR");
        Categoria categoriaSenador = categoriaService.buscarCategoriaPorDescricao("SENADOR");

        // Presidentes
        listaCandidatos.add(new Candidato(17, "Jair Bolsonaro", "jair_bolsas", "PSL", categoriaPresidente));
        listaCandidatos.add(new Candidato(13, "Fernando Haddad", "fernando_orfao", "PT", categoriaPresidente));
        listaCandidatos.add(new Candidato(12, "Ciro Gomes", "ciro_games", "PDT", categoriaPresidente));
        listaCandidatos.add(new Candidato(50, "Guilherme Boulos", "guilherme_bolos_e_doces", "PSOL", categoriaPresidente));
        listaCandidatos.add(new Candidato(30, "João Amoedo", "john_male_coin", "NOVO", categoriaPresidente));

        // Governadores
        listaCandidatos.add(new Candidato(55, "Ratinho JR", "lil_rat_jr", "PSD", categoriaGovernador));
        listaCandidatos.add(new Candidato(11, "Cida Borghetti", "cida_borg", "PP", categoriaGovernador));
        listaCandidatos.add(new Candidato(15, "João Arruda", "john_arwood", "MDB", categoriaGovernador));
        listaCandidatos.add(new Candidato(20, "Romeu Zema", "zomeu_rema", "NOVO", categoriaGovernador));

        // Senadores
        listaCandidatos.add(new Candidato(181, "Flávio Arns", "flavio_arns", "REDE", categoriaSenador));
        listaCandidatos.add(new Candidato(191, "Prof. Oriovisto", "prof_guimaraes", "PODE", categoriaSenador));
        listaCandidatos.add(new Candidato(457, "Mara Gabrilli", "mara_gabrilli", "PSDB", categoriaSenador));
        listaCandidatos.add(new Candidato(177, "Major Olímpio", "major_olimpio", "PSL", categoriaSenador));

        salvarCandidatos(listaCandidatos);
    }

    private void salvarCandidatos(List<Candidato> listaCandidatos) {
        for (Candidato candidato : listaCandidatos) {
            ContentValues values = new ContentValues();
            values.put("id", candidato.getId());
            values.put("nome", candidato.getNome());
            values.put("foto", candidato.getFoto());
            values.put("partido", candidato.getPartido());
            values.put("id_categoria", candidato.getCategoria().getId());
            dBHelper.getWritableDatabase().insert(TABLE_CANDIDATO, null, values);
        }
    }

}
