package br.com.integrador.projintegrador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;


public class SecundariaActivity extends AppCompatActivity {

    private Button btnCadEstacionamento, btnListarEstacionamento, btnCadLogin, btnLogout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secundaria);

        mAuth = FirebaseAuth.getInstance();

        btnCadEstacionamento = (Button) findViewById(R.id.btnCadEstacionamento);
        btnCadLogin = (Button) findViewById(R.id.btnCadLogin);
        btnListarEstacionamento = (Button) findViewById(R.id.btnListarEstacionamento);
        btnLogout = (Button) findViewById(R.id.btnLogout);

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
                Intent intent = new Intent(SecundariaActivity.this,Lista_Estacionamento.class);
                startActivity(intent);
            }
        });


        btnCadLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecundariaActivity.this,Cadastrar_UsuarioActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logoutUsuario();
            }
        });

    }

    private void logoutUsuario() {
        mAuth.signOut();
        finish();
    }

}
