package br.com.integrador.projintegrador;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class PrincipalActivity extends AppCompatActivity {

    private EditText edtLogin, edtSenha;
    private Button btnLogin, btnCadastrar;
    private Usuario usuarios;
    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        edtLogin = (EditText) findViewById(R.id.edtLogin);
        edtSenha = (EditText) findViewById(R.id.edtSenha);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnCadastrar = (Button) findViewById(R.id.btnCadastrar);

        mAuth = FirebaseAuth.getInstance();
        usuarios = new Usuario();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.setLoginUsuario(edtLogin.getText().toString());
                usuarios.setSenhaUsuario(edtSenha.getText().toString());

                //Chama o metodo verificaConexao para checar se o App está conectado a internet
                if (verificaConexao()) {
                    //Chama o método para autenticar o usuário no banco Firebase
                    autenticarUsuario(usuarios.getLoginUsuario().toString(),usuarios.getSenhaUsuario().toString());
                    edtLogin.setText("");
                    edtSenha.setText("");
                //    Toast.makeText(PrincipalActivity.this, "Conectado!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PrincipalActivity.this, "Aparentemente você está sem conexão!", Toast.LENGTH_LONG).show();
                }

            }
        });


        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PrincipalActivity.this,Cadastrar_UsuarioActivity.class);
                startActivity(intent);
            }
        });



    }


    /*
    Método encontrado na internet com o nome isOnline no endereço
    https://pt.stackoverflow.com/questions/29358/testar-conexao-com-a-internet-de-uma-aplica%C3%A7%C3%A3o
    modifiquei para o nome verificaConexao
    */
    public boolean verificaConexao() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return manager.getActiveNetworkInfo() != null &&
                manager.getActiveNetworkInfo().isConnectedOrConnecting();
    }



    /*
    Método encontrado no Git a partir da documentação do Firebase com o nome signIn no endereço
    https://github.com/firebase/quickstart-android/blob/master/auth/app/src/main/java/com/google/firebase/quickstart/auth/EmailPasswordActivity.java
    modifiquei para o nome autenticarUsuario
    */
    private void autenticarUsuario(String email, String password) {
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(PrincipalActivity.this, "Login efetuado com sucesso.",
                                    Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(PrincipalActivity.this,Cadastrar_EstacionamentoActivity.class);
                            startActivity(intent);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(PrincipalActivity.this, "Login ou senha inválidos.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

}
