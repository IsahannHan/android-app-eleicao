package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;

public class CategoriaService {

//    private static final String TABLE_CATEGORIA = MyApplication.getContext().getString(R.string.tbl_categoria);
    private static DBHelper dBHelper;

    public CategoriaService() {
        dBHelper = new DBHelper();
    }

    public static Categoria getCategoriaById(int idCategoria) {
        return lerCategoria(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM categoria WHERE id = ?;", new String[]{String.valueOf(idCategoria)}));
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

}
