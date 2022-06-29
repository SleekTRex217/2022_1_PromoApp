package com.giovanilopes.promoapp_2022_1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.UiAutomation;
import android.content.DialogInterface;
import android.opengl.EGLObjectHandle;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etNome, etTelefone, etEndereco, etNumero, etComplemento, etBairro, etEstado, etCidade;
    private Spinner spFarmacia, spMercado, spUtilidades, spTipoLog;
    private Button btnSalvar;
    private String action;
    private Cadastro cadastro;
    private RadioGroup rgCl;
    private RadioButton rbCell, rbHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spTipoLog = findViewById(R.id.spTipoLog);
        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        rgCl = findViewById(R.id.rgCl);
        rbCell = findViewById(R.id.rbCell);
        rbHome = findViewById(R.id.rbHome);
        etEndereco = findViewById(R.id.etEndereco);
        etNumero = findViewById(R.id.etNumero);
        etComplemento = findViewById(R.id.etComplemento);
        etBairro = findViewById(R.id.etBairro);
        etEstado = findViewById(R.id.etEstado);
        etCidade = findViewById(R.id.etCidade);
        spFarmacia = findViewById(R.id.spFarmacia);
        spMercado = findViewById(R.id.spMercado);
        spUtilidades = findViewById(R.id.spUtilidades);
        btnSalvar = findViewById(R.id.btnSalvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                salvar();
            }
        });

        carregarTipoEndereco();
        carregarFarmacias();
        carregarMercados();
        carregarLojas();

        action = getIntent().getExtras().getString("action");
        if (action.equals("edit")) {
            cadastro = new Cadastro();
            cadastro.setId(getIntent().getExtras().getString("idCliente"));
            etNome.setText(getIntent().getExtras().getString("nome"));
            etTelefone.setText(getIntent().getExtras().getString("telefone"));
            etEndereco.setText(getIntent().getExtras().getString("endereco"));
            etNumero.setText(getIntent().getExtras().getString("numero"));
            etComplemento.setText(getIntent().getExtras().getString("complemento"));
            etBairro.setText(getIntent().getExtras().getString("bairro"));
            etEstado.setText(getIntent().getExtras().getString("uf"));
            etCidade.setText(getIntent().getExtras().getString("cidade"));
        }
    }

    private void salvar() {
        if (action.equals("adicionar")) {
            cadastro = new Cadastro();
        }
        String nome = etNome.getText().toString();
        if (!nome.isEmpty() && spTipoLog.getSelectedItemPosition() > 0) {
            Toast.makeText(this,"Cadastro realizado com sucesso!", Toast.LENGTH_SHORT).show();
            cadastro.setNome(nome);
            int radioButtonReg =rgCl.getCheckedRadioButtonId();
            String rdCheck = ((RadioButton) findViewById(radioButtonReg)).getText().toString();
            if(rdCheck.equals("Cell")){
                cadastro.tipoTel = "Cell";
            } else {
                cadastro.tipoTel = "Home";
            }
            cadastro.setTelefone(etTelefone.getText().toString());
            cadastro.setTipoEndereco((TipoEndereco) spTipoLog.getSelectedItem());
            cadastro.setEndereco(etEndereco.getText().toString());
            cadastro.setNumero(etNumero.getText().toString());
            cadastro.setComplemento(etComplemento.getText().toString());
            cadastro.setBairro(etBairro.getText().toString());
            cadastro.setUf(etEstado.getText().toString());
            cadastro.setCidade(etCidade.getText().toString());
            cadastro.setFarmacia((Farmacia) spFarmacia.getSelectedItem());
            cadastro.setMercado((Mercado) spMercado.getSelectedItem());
            cadastro.setUtilidades((Utilidades) spUtilidades.getSelectedItem());

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            DatabaseReference reference = database.getReference();
            if (action.equals("adicionar")) {
                reference.child("clientes").push().setValue(cadastro);
            } else {
                reference.child("clientes").child(cadastro.getId()).setValue(cadastro);
            }
            finish();
        } else {
            Toast.makeText(this,"Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
        }
    }

    private void carregarTipoEndereco() {
        TipoEndereco falso = new TipoEndereco("0", "Selecione");
        TipoEndereco alameda = new TipoEndereco("1", "Alameda");
        TipoEndereco avenida = new TipoEndereco("2", "Avenida");
        TipoEndereco rua = new TipoEndereco("3", "Rua");
        TipoEndereco travessa = new TipoEndereco("4", "Travessa");
        TipoEndereco outros = new TipoEndereco("5", "Outros");

        List<TipoEndereco> listaTipoEndereco = new ArrayList<>();
        listaTipoEndereco.add(falso);
        listaTipoEndereco.add(alameda);
        listaTipoEndereco.add(avenida);
        listaTipoEndereco.add(rua);
        listaTipoEndereco.add(travessa);
        listaTipoEndereco.add(outros);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaTipoEndereco);
        spTipoLog.setAdapter(adapter);
    }

    private void carregarFarmacias() {
        Farmacia falso = new Farmacia("0", "Selecione a Farmácia");
        Farmacia agafarma = new Farmacia("1", "Agafarma");
        Farmacia associadas = new Farmacia("2", "Associadas");
        Farmacia drogaraia = new Farmacia("3", "Droga Raia");
        Farmacia panvel = new Farmacia("4", "Panvel");
        Farmacia saojoao = new Farmacia("5", "São João");

        List<Farmacia> listaFarmacia = new ArrayList<>();
        listaFarmacia.add(falso);
        listaFarmacia.add(agafarma);
        listaFarmacia.add(associadas);
        listaFarmacia.add(drogaraia);
        listaFarmacia.add(panvel);
        listaFarmacia.add(saojoao);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaFarmacia);
        spFarmacia.setAdapter(adapter);
    }

    private void carregarMercados() {
        Mercado falso = new Mercado("0", "Selecione o Mercado");
        Mercado asun = new Mercado("1", "Asun");
        Mercado carrefour = new Mercado("2", "Carrefour");
        Mercado maxxi = new Mercado("3", "Maxxi Atacado");
        Mercado rissul = new Mercado("4", "Supper Rissul");
        Mercado zaffari = new Mercado("5", "Zaffari");

        List<Mercado> listaMercado = new ArrayList<>();
        listaMercado.add(falso);
        listaMercado.add(asun);
        listaMercado.add(carrefour);
        listaMercado.add(maxxi);
        listaMercado.add(rissul);
        listaMercado.add(zaffari);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaMercado);
        spMercado.setAdapter(adapter);
    }

    private void carregarLojas() {
        Utilidades falso = new Utilidades("0","Selecione a Loja de Utilidades");
        Utilidades benoit = new Utilidades("1","Benoit");
        Utilidades lebes = new Utilidades("2","Lebes");
        Utilidades colombo = new Utilidades("3","Lojas Colombo");
        Utilidades magalu = new Utilidades("4","Magazine Luiza");
        Utilidades taqi = new Utilidades("5","TaQi");

        List<Utilidades> listaLojas = new ArrayList<>();
        listaLojas.add(falso);
        listaLojas.add(benoit);
        listaLojas.add(lebes);
        listaLojas.add(colombo);
        listaLojas.add(magalu);
        listaLojas.add(taqi);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaLojas);
        spUtilidades.setAdapter(adapter);
    }

}