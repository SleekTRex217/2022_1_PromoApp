package com.giovanilopes.promoapp_2022_1;

public class Farmacia {

    private String id, nome;

    public Farmacia() {

    }

    public Farmacia(String nome) {this.nome = nome;}

    public Farmacia(String id, String nome){
        this.id = id;
        this.nome = nome;
    }

    public String toString() {return nome;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}
}
