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

public class CategoriaService {

    private static final String TABLE_CATEGORIA = MyApplication.getContext().getString(R.string.tbl_categoria);
    private static DBHelper dBHelper;

    public CategoriaService() {
        dBHelper = new DBHelper();
    }

    public static Categoria buscarCategoriaPorId(int idCategoria) {
        return lerCategoria(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria WHERE id = ?;", new String[]{String.valueOf(idCategoria)}));
    }

    public static Categoria buscarCategoriaPorDescricao(String descricao) {
        return lerCategoria(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria WHERE descricao = '?';", new String[]{descricao}));
    }

    // Métodos auxiliares

    private static Categoria lerCategoria(Cursor cursor) {
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
