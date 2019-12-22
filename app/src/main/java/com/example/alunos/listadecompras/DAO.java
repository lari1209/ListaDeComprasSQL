package com.example.alunos.listadecompras;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DAO {

    // Instância da OpenHelper para abrir o banco de dados
    private ListaOpenHelper openHelper;
    // Objeto que será usada para realizar as operações (CRUD)
    // no banco de dados
    private SQLiteDatabase sqLiteDatabase;

    // Construtor da classe DAO. Quando é chamado, instancia o
    // objeto "openHelper", que será usado para abrir o banco.
    // O parâmetro "name" é um nome qualquer para o arquivo do banco
    public DAO(Context context) {
        openHelper = new ListaOpenHelper(context, "lista.db",
                null, 1);
    }

    private void abrir() {
        sqLiteDatabase = openHelper.getWritableDatabase();
    }

    private void fechar() {
        if (sqLiteDatabase != null) sqLiteDatabase.close();
    }

    public ArrayList<Item> consultarItens() {
        abrir();
        ArrayList<Item> itens = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.query("lista",
                null, null, null,
                null, null, "descricao");
        while (cursor.moveToNext()) {
            Item item = new Item();
            item.setId(cursor.getInt(0));
            // Obtém na tabela o valor para o campo 1 (descricao)
            // e coloca no objeto "item"
            item.setDescricao(cursor.getString(1));
            // Obtém na tabela o valor para o campo 2 (quantidade)
            // e coloca no objeto "item"
            item.setQuantidade(cursor.getInt(2));
            itens.add(item);
        }
        fechar();
        return itens;
    }

    public void incluirItem(Item item) {
        abrir();
        // O objeto "values" servirá para guardar os valores para
        // os campos
        ContentValues values = new ContentValues();
        // Para o campo "descricao" da tabela, será colocado o valor
        // existente no campo "descricao" do objeto "item" (obtido com
        // item.getDescricao)
        values.put("descricao", item.getDescricao());
        // Mesma coisa, só que agora para o campo "quantidade"
        values.put("quantidade", item.getQuantidade());
        // Este comando faz o "INSERT" no tabela "itens", usando os
        // valores colocados em "values" (terceiro parâmetro)
        sqLiteDatabase.insert("lista", null, values);
        fechar();
    }

    public void excluirItem(Item item) {
        abrir();
        // Excluímos da tabela "lista" o registro com "_id" igual
        // ao "id" do objeto "item" recebido como parâmetro
        sqLiteDatabase.delete("lista", "_id = " +
            item.getId(), null);
        fechar();
    }
}
