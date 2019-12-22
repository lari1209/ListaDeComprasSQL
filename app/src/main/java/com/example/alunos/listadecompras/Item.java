package com.example.alunos.listadecompras;

public class Item {

    // O campo "id" na classe Item corresponderá ao
    // campo "_id" na tabela "itens" do banco de dados.
    private int id;
    private String descricao;
    private int quantidade;

    // Getter e Setter para o campo "id"
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return descricao + "\n" + "Quantidade: " + quantidade;
    }
}
