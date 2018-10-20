package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity;

public class Categoria {

    private int id;
    private String descricao;

    public Categoria(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }
    public String getDescricao() {
        return descricao;
    }
}
