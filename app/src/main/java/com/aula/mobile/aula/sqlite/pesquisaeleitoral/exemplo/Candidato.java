package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo;

public class Candidato {

    private int id;
    private String nome;
    private String partido;
    private int idCategoria;

    public Candidato(int id, String nome, String partido) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
    }

    public int getId() {
        return id;
    }


    public String getNome() {
        return nome;
    }


    public String getPartido() {
        return partido;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    @Override
    public String toString() {
        return nome + " - " + partido;
    }
}
