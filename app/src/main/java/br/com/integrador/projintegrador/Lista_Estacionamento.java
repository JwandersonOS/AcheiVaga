package br.com.integrador.projintegrador;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ruben on 14/06/2017.
 */

public class Lista_Estacionamento extends AppCompatActivity {
    private DatabaseReference databaseReference = FirebaseDatabase.
            getInstance().getReference();
    private DatabaseReference estacionamentoReference =
            databaseReference.child("estacionamento");
    private ProgressDialog dialog;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_estacionamento);

        final ListView listView = (ListView) findViewById(R.id.Lista);

        dialog = ProgressDialog.show(this, "Lista", "Listando estacionamentos, aguarde...", true, false);

        estacionamentoReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> dsEstacionamento = dataSnapshot.getChildren();
                List<Estacionamento> estacionamento = new ArrayList<Estacionamento>();
                while(dsEstacionamento.iterator().hasNext()){
                    DataSnapshot dsObjetoCliente =
                            dsEstacionamento.iterator().next();
                    Estacionamento estacio =
                            dsObjetoCliente.getValue(Estacionamento.class);
                    estacionamento.add(estacio);
                }
                ArrayAdapter<Estacionamento> adapter =
                        new ArrayAdapter<Estacionamento>(Lista_Estacionamento.this,
                                android.R.layout.simple_list_item_1,
                                estacionamento);
                listView.setAdapter(adapter);

                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
