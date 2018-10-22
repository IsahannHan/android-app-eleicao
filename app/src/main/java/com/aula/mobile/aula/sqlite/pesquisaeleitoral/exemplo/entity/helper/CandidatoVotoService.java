package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.database.DBHelper;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.CandidatoVoto;

import java.util.ArrayList;
import java.util.List;

public class CandidatoVotoService {

    public final String TABLE_CANDIDATO_VOTO = "candidato_voto";
    private Context context;
    private DBHelper dBHelper;

    public CandidatoVotoService(Context context) {
        this.context = context;
        dBHelper = new DBHelper(context);
    }

    public List<CandidatoVoto> buscarResultadoGeral() {
        return lerListaVotos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato_voto;", null));
    }

    public List<CandidatoVoto> buscarResultadoPorCategoria(int idCategoria) {
        return lerListaVotos(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato_voto cv " +
                        " INNER JOIN candidato c ON cv.id_candidato =  c.id " +
                        " WHERE c.id_categoria = ?" +
                        " ORDER BY cv.numero_votos DESC;", new String[]{String.valueOf(idCategoria)}));
    }

    public CandidatoVoto buscarResultadoPorCandidato(int idCandidato) {
        return lerVoto(dBHelper.getReadableDatabase()
                .rawQuery("SELECT * FROM candidato_voto WHERE id_candidato = ?;", new String[]{String.valueOf(idCandidato)}));
    }

    public void realizarVotoEmCandidato(int idCandidato) {
        if(lerVoto(dBHelper.getReadableDatabase().rawQuery("SELECT * FROM candidato_voto WHERE id_candidato = ?", new String[]{String.valueOf(idCandidato)})) == null){
            // insert
            ContentValues values = new ContentValues();

            values.put("id_candidato", idCandidato);
            values.put("numero_votos", 1);

            dBHelper.getWritableDatabase().insert(TABLE_CANDIDATO_VOTO, null, values);
        } else {
            ContentValues values = new ContentValues();
            CandidatoVoto votoAtual = lerVoto(dBHelper.getReadableDatabase().rawQuery("SELECT * FROM candidato_voto WHERE id_candidato = ?", new String[]{String.valueOf(idCandidato)}));

            values.put("id_candidato", idCandidato);
            values.put("numero_votos", votoAtual.getNumeroVotos() + 1);

            dBHelper.getWritableDatabase()
                    .update(TABLE_CANDIDATO_VOTO, values, "id_candidato = ?", new String[]{String.valueOf(idCandidato)});
        }
    }

    // Métodos auxiliares

    private List<CandidatoVoto> lerListaVotos(Cursor cursor) {
        CandidatoService candidatoService = new CandidatoService(context);
        List<CandidatoVoto> listaCandidatos = new ArrayList<>();

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Candidato candidato = candidatoService.buscarCandidatoPorId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("id_candidato"))));
            int numeroVotos = Integer.valueOf(cursor.getString(cursor.getColumnIndex("numero_votos")));

            listaCandidatos.add(new CandidatoVoto(id, candidato, numeroVotos));
        }

        return listaCandidatos;
    }

    private CandidatoVoto lerVoto(Cursor cursor) {
        CandidatoService candidatoService = new CandidatoService(context);
        CandidatoVoto candidatoVoto = null;

        for (cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("id"));
            Candidato candidato = candidatoService.buscarCandidatoPorId(Integer.valueOf(cursor.getString(cursor.getColumnIndex("id_candidato"))));
            int numeroVotos = Integer.valueOf(cursor.getString(cursor.getColumnIndex("numero_votos")));

            candidatoVoto = new CandidatoVoto(id, candidato, numeroVotos);
        }

        return candidatoVoto;
    }
}
