package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aula.mobile.aula.R;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoService;

import java.util.List;

public class EleicaoVotoActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvText;
    EleicaoVotoActivity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleicao_voto);

        listView = findViewById(R.id.list);
        tvText = findViewById(R.id.tvText);

        int idCategoria = this.getIntent().getIntExtra("id", 0);
        ECategoria eCategoria = ECategoria.getById(idCategoria);
        tvText.setText("Pesquisa para: " + eCategoria.getNome() + " - " + eCategoria.getEstado());


        CandidatoService candidatoService = new CandidatoService();
        List<Candidato> candidatos = candidatoService.getList(eCategoria.getId());

        ArrayAdapter<Candidato> adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, candidatos);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Candidato item = (Candidato) parent.getItemAtPosition(position);
                new AlertDialog.Builder(activity)
                        .setIcon(R.drawable.ic_vote)
                        .setTitle("Pesquisa eleitoral")
                        .setMessage("Confirma seu voto em: " + item.getNome())
                        .setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "Implementar", Toast.LENGTH_SHORT).show();
                            }

                        })
                        .setNegativeButton("NAO", null)
                        .show();
            }
        });
    }

}
