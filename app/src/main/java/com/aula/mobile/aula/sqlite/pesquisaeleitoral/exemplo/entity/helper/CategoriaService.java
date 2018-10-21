package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;

import java.util.ArrayList;
import java.util.List;

public class CategoriaService {

    private Context context;
    private final String TABLE_CATEGORIA = "categoria_candidato";
    private DBHelper dBHelper;

    public CategoriaService(Context context) {
        this.context = context;
        dBHelper = new DBHelper(context);
    }

    public List<Categoria> buscarCategorias(){
        return lerListaCategorias(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria_candidato ", null));
    }

    public Categoria buscarCategoriaPorId(int idCategoria) {
        return lerCategoria(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria_candidato WHERE id = ?;", new String[]{String.valueOf(idCategoria)}));
    }

    public Categoria buscarCategoriaPorDescricao(String descricao) {
        return lerCategoria(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria_candidato WHERE descricao LIKE ?;", new String[]{descricao}));
    }

    // Métodos auxiliares

    private List<Categoria> lerListaCategorias(Cursor cursor) {
        List<Categoria> listaCategorias = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            listaCategorias.add(new Categoria(id, descricao));
        }

        return listaCategorias;
    }

    private Categoria lerCategoria(Cursor cursor) {
        Categoria categoria = null;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            String descricao = cursor.getString(cursor.getColumnIndex("descricao"));

            categoria = new Categoria(id, descricao);
        }

        return categoria;
    }

    // Métodos para preparar o banco de dados

    public void adicionarCategorias() {
        List<Categoria> listaCategorias = new ArrayList<>();

        // Presidentes
        listaCategorias.add(new Categoria(1, "PRESIDENTE"));
        listaCategorias.add(new Categoria(2, "GOVERNADOR"));
        listaCategorias.add(new Categoria(3, "SENADOR"));

        salvarCategorias(listaCategorias);
    }

    private void salvarCategorias(List<Categoria> listaCategorias) {
        for (Categoria categoria : listaCategorias) {
            ContentValues values = new ContentValues();
            values.put("id", categoria.getId());
            values.put("descricao", categoria.getDescricao());
            dBHelper.getWritableDatabase().insert(TABLE_CATEGORIA, null, values);
        }
    }
}
