package com.giovanilopes.promoapp_2022_1;

public class TipoEndereco {

    private String id, descricao;

    public TipoEndereco() {

    }

    public TipoEndereco(String descricao) {this.descricao = descricao;}

    public TipoEndereco(String id, String descricao){
        this.id = id;
        this.descricao = descricao;
    }

    public String toString() {return descricao;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getDescricao() {return descricao;}

    public void setDescricao(String descricao) {this.descricao = descricao;}

}
