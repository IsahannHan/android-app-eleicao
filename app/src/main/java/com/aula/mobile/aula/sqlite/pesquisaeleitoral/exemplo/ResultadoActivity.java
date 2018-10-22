package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter.CandidatoAdapter;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter.ResultadoAdapter;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter.SpinAdapter;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.CandidatoVoto;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoVotoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CategoriaService;

import java.util.List;

public class ResultadoActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        ListView listView = findViewById(R.id.lv_candidatos_votos);

        CandidatoVotoService candidatoVotoService = new CandidatoVotoService(getApplicationContext());
        CategoriaService categoriaService = new CategoriaService(getApplicationContext());
        int idCategoria = this.getIntent().getIntExtra("id", 0);
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);

        TextView textViewCategoria = findViewById(R.id.tv_resultado_categoria);
        textViewCategoria.setText("Resultado para " + categoria.getDescricao());

        List<CandidatoVoto> listaVotos = candidatoVotoService.buscarResultadoPorCategoria(idCategoria);

        ResultadoAdapter adapter =
                new ResultadoAdapter(getApplicationContext(), listaVotos, this);

        listView.setAdapter(adapter);
    }
}
