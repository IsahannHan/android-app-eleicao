package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter.SpinAdapter;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.CandidatoVoto;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoVotoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CategoriaService;

import java.util.List;

public class EleicaoActivity extends AppCompatActivity {

    private SpinAdapter spinAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleicao);

//        DBHelper dbHelper = new DBHelper(getApplicationContext());
        CandidatoService candidatoService = new CandidatoService(getApplicationContext());
        final CandidatoVotoService candidatoVotoService = new CandidatoVotoService(getApplicationContext());
        CategoriaService categoriaService = new CategoriaService(getApplicationContext());

        /*********BANCO DE DADOS**************/
        if (categoriaService.buscarCategorias().size() == 0)
            categoriaService.adicionarCategorias();

        if (candidatoService.buscarCandidatos().size() == 0)
            candidatoService.adicionarCandidatos();
        /***********************/

        final Spinner spCategoria = findViewById(R.id.spCategoria);
        Button btIniciar = findViewById(R.id.btIniciar);
        Button btResultado = findViewById(R.id.btResultado);

        List<Categoria> listaCategorias = categoriaService.buscarCategorias();
        spinAdapter = new SpinAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, listaCategorias);

        spCategoria.setAdapter(spinAdapter);

        btIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = (Categoria) spCategoria.getSelectedItem();
                Intent intent = new Intent(EleicaoActivity.this, EleicaoVotoActivity.class);
                intent.putExtra("id", categoria.getId());
                startActivity(intent);

            }
        });

        btResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Categoria categoria = (Categoria) spCategoria.getSelectedItem();
                Intent intent = new Intent(EleicaoActivity.this, ResultadoActivity.class);
                intent.putExtra("id", categoria.getId());
                startActivity(intent);
            }
        });
    }
}
