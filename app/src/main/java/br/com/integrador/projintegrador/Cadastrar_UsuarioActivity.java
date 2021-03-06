package br.com.integrador.projintegrador;

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

public class Cadastrar_UsuarioActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private EditText edtCadEmail, edtCadSenha, edtConfSenha;
    private Button btnCadUsuario;
    private Usuario usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar__usuario);

        edtCadEmail = (EditText) findViewById(R.id.edtCadEmail);
        edtCadSenha = (EditText) findViewById(R.id.edtCadSenha);
        edtConfSenha = (EditText) findViewById(R.id.edtConfSenha);
        btnCadUsuario = (Button) findViewById(R.id.btnCadUsuario);

        mAuth = FirebaseAuth.getInstance();
        usuarios = new Usuario();

        btnCadUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usuarios.setLoginUsuario(edtCadEmail.getText().toString());
                usuarios.setSenhaUsuario((edtCadSenha.getText().toString()));

                if (edtCadSenha.getText().toString().equals(edtConfSenha.getText().toString())){
                    criarConta(usuarios.getLoginUsuario().toString(), usuarios.getSenhaUsuario().toString());
                    edtCadEmail.setText("");
                    edtCadSenha.setText("");
                    edtConfSenha.setText("");
                    Toast.makeText(Cadastrar_UsuarioActivity.this, "Cadastro de usuário efetuado com sucesso." ,
                            Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(Cadastrar_UsuarioActivity.this, "As senhas digitadas são diferentes." ,
                            Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void criarConta(String email, String password) {
        Log.d(TAG, "createAccount:" + email);

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Cadastrar_UsuarioActivity.this, "Ocorreu um erro na criação da conta do usuário.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                    }
                });
    }

}
