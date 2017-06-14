package br.com.integrador.projintegrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SecundariaActivity extends AppCompatActivity {

    private Button btnCadEstacionamento, btnListarEstacionamento, btnCadLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        btnCadEstacionamento = (Button) findViewById(R.id.btnCadEstacionamento);
        btnCadLogin = (Button) findViewById(R.id.btnCadLogin);
        btnListarEstacionamento = (Button) findViewById(R.id.btnListarEstacionamento);

        btnCadEstacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecundariaActivity.this,Cadastrar_EstacionamentoActivity.class);
                startActivity(intent);
            }
        });

        btnListarEstacionamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //
            }
        });


        btnCadLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecundariaActivity.this,Cadastrar_UsuarioActivity.class);
                startActivity(intent);
            }
        });
    }
}
