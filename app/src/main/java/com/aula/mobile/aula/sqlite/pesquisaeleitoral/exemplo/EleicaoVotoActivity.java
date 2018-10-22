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
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.adapter.CandidatoAdapter;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Candidato;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.Categoria;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CandidatoVotoService;
import com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity.helper.CategoriaService;

import java.util.List;

public class EleicaoVotoActivity extends AppCompatActivity {

    private ListView listView;
    private TextView tvText;
    EleicaoVotoActivity activity = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eleicao_voto);

        listView = findViewById(R.id.lv_candidatos);
        tvText = findViewById(R.id.tvText);

        int idCategoria = this.getIntent().getIntExtra("id", 0);

        CategoriaService categoriaService = new CategoriaService(getApplicationContext());
        Categoria categoria = categoriaService.buscarCategoriaPorId(idCategoria);

        tvText.setText("Clique para votar em " + categoria.getDescricao());

        final CandidatoService candidatoService = new CandidatoService(getApplicationContext());
        final CandidatoVotoService candidatoVotoService = new CandidatoVotoService(getApplicationContext());
        List<Candidato> candidatos = candidatoService.buscarCandidatosPorCategoria(idCategoria);

        CandidatoAdapter adapter =
                new CandidatoAdapter(getApplicationContext(), candidatos, this);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Candidato candidato = (Candidato) parent.getItemAtPosition(position);
                new AlertDialog.Builder(activity)
                        .setIcon(R.drawable.ic_vote)
                        .setTitle("Cofirmar voto")
                        .setMessage("Deseja confirmar seu voto em " + candidato.getNome() +" ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                candidatoVotoService.realizarVotoEmCandidato(candidato.getId());
                                if(candidato.getNome().equals("Cabo Daciolo")){
                                    Toast.makeText(getApplicationContext(), "Voto computado com sucesso! \n GLÓRIA A DEUXXXX", Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "Voto computado com sucesso!", Toast.LENGTH_SHORT).show();
                                }
                            }

                        })
                        .setNegativeButton("Não", null)
                        .show();
            }
        });
    }

}
