package com.example.alunos.listadecompras;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Item> lista;
    private ArrayAdapter<Item> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final DAO dao = new DAO(this);
        lista = dao.consultarItens();

        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_checked,
                lista);

        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent,
                                           View view, int position, long id) {
                // Quando removemos um elemento do ArrayList, este é
                // retornado pelo método "remove". Assim, podemos
                // guardá-lo em um objeto "temporário", como este
                // "itemRemovido", que será usado logo abaixo
                Item itemRemovido = lista.remove(position);
                // Chamamos o método "excluirItem" da "dao" passando
                // para ele o objeto que acabou de ser removido
                // do ArrayList
                dao.excluirItem(itemRemovido);
                adapter.notifyDataSetChanged();
                return false;
            }
        });
    }

    public void adicionar(View view) {
        EditText edtDesc = (EditText)findViewById(R.id.edtDesc);
        EditText edtQtd = (EditText)findViewById(R.id.edtQtd);

        String descricao = edtDesc.getText().toString();
        String quantidade = edtQtd.getText().toString();

        if (descricao.isEmpty() || quantidade.isEmpty()) {
            Toast.makeText(this, "Informe uma" +
                    " descrição e uma quantidade para o item.",
                    Toast.LENGTH_LONG).show();
        } else {
            Item item = new Item();
            item.setDescricao(descricao);
            item.setQuantidade(Integer.parseInt(quantidade));
            lista.add(item);

            // Instanciamos a classe DAO
            DAO dao = new DAO(this);
            // Incluimos o objeto "item" no banco de dados
            dao.incluirItem(item);

            adapter.notifyDataSetChanged();
            edtDesc.setText("");
            edtQtd.setText("");
        }

    }
}
