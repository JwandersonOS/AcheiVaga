package br.com.integrador.projintegrador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Cadastrar_EstacionamentoActivity extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference estacionamentoReference = databaseReference.child("estacionamento");

    private Button btnSalvar, btnListar;
    private EditText edtPropriet, edtEstac, edtCNPJ, edtTelefone,
                     edtEndereco, edtNumero, edtBairro, edtCidade,
                     edtQtdVagas, edtServico, edtEmail, edtSenha;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__estacionamento);

        final EditText edtPropriet = (EditText) findViewById(R.id.edtPropriet);
        final EditText edtEstac = (EditText) findViewById(R.id.edtEstac);

        Button btnSalvar = (Button) findViewById(R.id.btnSalvar);

        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Estacionamento estacionamento = new Estacionamento();
                estacionamento.setProprietEstacionamento(edtPropriet.getText().toString());
                estacionamento.setNomeEstacionamento(edtEstac.getText().toString());

                estacionamentoReference.child(edtPropriet.getText().toString()).setValue(estacionamento);

                edtPropriet.setText("");
                edtEstac.setText("");

            }
        });




    }
}
