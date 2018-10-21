package com.aula.mobile.aula.sqlite.pesquisaeleitoral.exemplo.entity;

public class CandidatoVoto {

    private int id;
    private Candidato candidato;
    private int numeroVotos;

    public CandidatoVoto(int id, Candidato candidato, int numeroVotos) {
        this.id = id;
        this.candidato = candidato;
        this.numeroVotos = numeroVotos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public int getNumeroVotos() {
        return numeroVotos;
    }

    public void setNumeroVotos(int numeroVotos) {
        this.numeroVotos = numeroVotos;
    }
}
