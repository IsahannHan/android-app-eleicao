package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;

import java.util.List;

public class CandidatoAdapter extends BaseAdapter {

    private final List<Candidato> listaCandidatos;
    private final Activity activity;
    private Context context;

    public CandidatoAdapter(Context context, List<Candidato> listaCandidatos, Activity activity) {
        this.context = context;
        this.listaCandidatos = listaCandidatos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaCandidatos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaCandidatos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaCandidatos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater()
                .inflate(R.layout.linha_candidatos, parent, false);

        Candidato candidato = listaCandidatos.get(position);

        String uri = "@drawable/" + candidato.getFoto();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        TextView nome = view.findViewById(R.id.lv_nome_candidato);
        TextView numero = view.findViewById(R.id.lv_numero_candidato);
        TextView partido = view.findViewById(R.id.lv_partido_candidato);
        ImageView imagem = view.findViewById(R.id.lv_imagem_candidato);

        nome.setText(candidato.getNome());
        numero.setText("Número " + String.valueOf(candidato.getId()));
        partido.setText(candidato.getPartido());
        imagem.setImageDrawable(context.getResources().getDrawable(imageResource));

        return view;
    }
}
