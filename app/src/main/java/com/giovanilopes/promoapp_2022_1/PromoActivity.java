package com.giovanilopes.promoapp_2022_1;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class PromoActivity extends AppCompatActivity {

    private ListView lvCadastro;
    private Button btnAdicionar;
    private List<Cadastro> listaCadastro = new ArrayList<>();
    private ArrayAdapter adapter;

    FirebaseDatabase database;
    DatabaseReference reference;
    ChildEventListener childEventListener;
    Query query;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_promo);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference();

        lvCadastro = findViewById(R.id.lvCadastro);
        btnAdicionar = findViewById(R.id.btnAdicionar);

        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                intent.putExtra("action", "adicionar");
                startActivity(intent);
            }
        });

        lvCadastro.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            public void onItemClick(AdapterView<?> parent, View view, int position, long id){
                Cadastro cadastroSelect = listaCadastro.get(position);

                Intent intent = new Intent(PromoActivity.this, MainActivity.class);
                intent.putExtra("action","edit");
                intent.putExtra("idCliente", cadastroSelect.getId());
                intent.putExtra("nome", cadastroSelect.getNome());
                intent.putExtra("tipoTel", cadastroSelect.getTipoTel());
                intent.putExtra("telefone", cadastroSelect.getTelefone());
                intent.putExtra("endereco", cadastroSelect.getEndereco());
                intent.putExtra("numero", cadastroSelect.getNumero());
                intent.putExtra("complemento", cadastroSelect.getComplemento());
                intent.putExtra("bairro", cadastroSelect.getBairro());
                intent.putExtra("cidade", cadastroSelect.getCidade());
                intent.putExtra("uf", cadastroSelect.getUf());
                startActivity(intent);
            }
        });

        lvCadastro.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                excluir(position);
                return true;
            }
        });
    }

    private void excluir(int posicao){
        Cadastro cadastro = listaCadastro.get( posicao );
        AlertDialog.Builder alerta = new AlertDialog.Builder(this);
        alerta.setTitle("Exclusão de Cadastro");
        alerta.setIcon(android.R.drawable.ic_delete);
        alerta.setMessage("Tem certeza que deseja excluir o cadastro de " + cadastro.getNome() +"?");
        alerta.setNeutralButton("Cancelar", null);
        alerta.setPositiveButton("Excluir", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                reference.child("clientes").child(cadastro.getId()).removeValue();
            }
        });
        alerta.show();
    }

    protected void onStart(){
        super.onStart();
        carregarCadastro();
        listaCadastro.clear();

        query = reference.child("clientes").orderByChild("nome");
        childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Cadastro c = new Cadastro();
                c.setId(snapshot.getKey());
                c.setNome(snapshot.child("nome").getValue(String.class));
                c.setTipoTel(snapshot.child("tipoTel").getValue(String.class));
                c.setTelefone(snapshot.child("telefone").getValue(String.class));
                c.setTipoEndereco(snapshot.child("tipoEndereco").getValue(TipoEndereco.class));
                c.setEndereco(snapshot.child("endereco").getValue(String.class));
                c.setNumero(snapshot.child("numero").getValue(String.class));
                c.setComplemento(snapshot.child("complemento").getValue(String.class));
                c.setBairro(snapshot.child("bairro").getValue(String.class));
                c.setCidade(snapshot.child("cidade").getValue(String.class));
                c.setUf(snapshot.child("uf").getValue(String.class));
                c.setFarmacia(snapshot.child("farmacia").getValue(Farmacia.class));
                c.setMercado(snapshot.child("mercado").getValue(Mercado.class));
                c.setUtilidades(snapshot.child("utilidades").getValue(Utilidades.class));
                listaCadastro.add(c);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String idCliente = snapshot.getKey();
                for(Cadastro c : listaCadastro){
                    if(c.getId().equals(idCliente)){
                        c.setNome(snapshot.child("nome").getValue(String.class));
                        c.setTipoTel(snapshot.child("tipoTel").getValue(String.class));
                        c.setTelefone(snapshot.child("telefone").getValue(String.class));
                        c.setTipoEndereco(snapshot.child("tipoEndereco").getValue(TipoEndereco.class));
                        c.setEndereco(snapshot.child("endereco").getValue(String.class));
                        c.setNumero(snapshot.child("numero").getValue(String.class));
                        c.setComplemento(snapshot.child("complemento").getValue(String.class));
                        c.setBairro(snapshot.child("bairro").getValue(String.class));
                        c.setCidade(snapshot.child("cidade").getValue(String.class));
                        c.setUf(snapshot.child("uf").getValue(String.class));
                        c.setFarmacia(snapshot.child("farmacia").getValue(Farmacia.class));
                        c.setMercado(snapshot.child("mercado").getValue(Mercado.class));
                        c.setUtilidades(snapshot.child("utilidades").getValue(Utilidades.class));
                        break;
                    }
                }

                adapter.notifyDataSetChanged();
            }

            public void onChildRemoved(@NonNull DataSnapshot snapshot){
                String idCliente = snapshot.getKey();
                for(Cadastro c: listaCadastro){
                    if(c.getId().equals(idCliente)){
                        listaCadastro.remove(c);
                        break;
                    }
                }
                adapter.notifyDataSetChanged();
            }

            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName){

            }

            public void onCancelled(@NonNull DatabaseError error){

            }
        };
        query.addChildEventListener(childEventListener);
    }

    protected void onStop(){
        super.onStop();
        query.removeEventListener(childEventListener);
    }

    private void carregarCadastro(){
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCadastro);
        lvCadastro.setAdapter(adapter);
    }
}