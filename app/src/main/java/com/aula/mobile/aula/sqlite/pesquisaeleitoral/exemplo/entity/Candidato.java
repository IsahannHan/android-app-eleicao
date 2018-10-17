package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity;

public class Candidato {

    private int id;
    private String nome;
    private String partido;
    private Categoria categoria;

    public Candidato(int id, String nome, String partido, Categoria categoria) {
        this.id = id;
        this.nome = nome;
        this.partido = partido;
        this.categoria = categoria;
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

    public Categoria getCategoria() {
        return categoria;
    }

    @Override
    public String toString() {
        return "Candidato{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", partido='" + partido + '\'' +
                ", categoria=" + categoria +
                '}';
    }
}
