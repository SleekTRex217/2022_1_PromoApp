package com.giovanilopes.promoapp_2022_1;

public class Cadastro {

    public String id, nome, tipoTel, telefone, endereco, numero, complemento, bairro, cidade, uf;
    public Farmacia farmacia;
    public Mercado mercado;
    public Utilidades utilidades;
    public TipoEndereco tipoEndereco;

    public Cadastro() {

    }

    public Cadastro(String nome, String tipoTel, String telefone, String endereco, String numero, String complemento,
                    String bairro, String cidade, String uf, Farmacia farmacia, Mercado mercado,
                    Utilidades utilidades, TipoEndereco tipoEndereco){
        this.nome = nome;
        this.tipoTel = tipoTel;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.farmacia = farmacia;
        this.mercado = mercado;
        this.utilidades = utilidades;
        this.tipoEndereco = tipoEndereco;
    }

    public Cadastro(String id, String nome, String tipoTel, String telefone, String endereco, String numero, String complemento,
                    String bairro, String cidade, String uf, Farmacia farmacia, Mercado mercado,
                    Utilidades utilidades, TipoEndereco tipoEndereco){
        this.id = id;
        this.nome = nome;
        this.tipoTel = tipoTel;
        this.telefone = telefone;
        this.endereco = endereco;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cidade = cidade;
        this.uf = uf;
        this.farmacia = farmacia;
        this.mercado = mercado;
        this.utilidades = utilidades;
        this.tipoEndereco = tipoEndereco;
    }

    public String toString() {return nome + "\n" + telefone + "\n" + cidade + " / " + uf;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getNome() {return nome;}

    public void setNome(String nome) {this.nome = nome;}

    public String getTelefone() {return telefone;}

    public void setTelefone(String telefone) {this.telefone = telefone;}

    public String getTipoTel() {return tipoTel;}

    public void setTipoTel(String tipoTel) {this.tipoTel = tipoTel;}

    public String getEndereco() {return endereco;}

    public void setEndereco(String endereco) {this.endereco = endereco;}

    public String getNumero() {return numero;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getComplemento() {return complemento;}

    public void setComplemento(String complemento) {this.complemento = complemento;}

    public String getBairro() {return bairro;}

    public void setBairro(String bairro) {this.bairro = bairro;}

    public String getCidade() {return cidade;}

    public void setCidade(String cidade) {this.cidade = cidade;}

    public String getUf() {return uf;}

    public void setUf(String uf) {this.uf = uf;}

    public Farmacia getFarmacia() {return farmacia;}

    public void setFarmacia(Farmacia farmacia) {this.farmacia = farmacia;}

    public Mercado getMercado() {return mercado;}

    public void setMercado(Mercado mercado) {this.mercado = mercado;}

    public Utilidades getUtilidades() {return utilidades;}

    public void setUtilidades(Utilidades utilidades) {this.utilidades = utilidades;}

    public TipoEndereco getTipoEndereco() {return tipoEndereco;}

    public void setTipoEndereco(TipoEndereco tipoEndereco) {this.tipoEndereco = tipoEndereco;}
}
