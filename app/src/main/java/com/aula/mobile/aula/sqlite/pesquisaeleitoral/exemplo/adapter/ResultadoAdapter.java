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
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.CandidatoVoto;

import java.util.List;

public class ResultadoAdapter extends BaseAdapter {

    private final List<CandidatoVoto> listaVotos;
    private final Activity activity;
    private Context context;

    public ResultadoAdapter(Context context, List<CandidatoVoto> listaVotos, Activity activity) {
        this.context = context;
        this.listaVotos = listaVotos;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return listaVotos.size();
    }

    @Override
    public Object getItem(int position) {
        return listaVotos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return listaVotos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = activity.getLayoutInflater()
                .inflate(R.layout.linha_candidatos_votos, parent, false);

        CandidatoVoto voto = listaVotos.get(position);

        String uri = "@drawable/" + voto.getCandidato().getFoto();
        int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());

        TextView nome = view.findViewById(R.id.lv_nome_candidato_votos);
        TextView numero = view.findViewById(R.id.lv_numero_candidato_votos);
        ImageView imagem = view.findViewById(R.id.lv_imagem_candidato_votos);

        nome.setText(voto.getCandidato().getNome());
        numero.setText(String.valueOf(voto.getNumeroVotos()));
        imagem.setImageDrawable(context.getResources().getDrawable(imageResource));

        return view;
    }
}
