package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class Confirm extends AppCompatActivity {

    ArrayList<String> names,team,pos;
    ArrayList<String> list;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    DocumentReference documentReference;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirm);
        uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
        names = new ArrayList<>();
        pos = new ArrayList<>();
        team = new ArrayList<>();
        list = getIntent().getStringArrayListExtra("list");

        for(String item: list){
            documentReference =db.collection("players").document(item);
            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()){
                        DocumentSnapshot snapshot =task.getResult();
                        if (snapshot.exists()){
                            names.add(snapshot.getString("name"));
                            team.add(snapshot.getString("team"));
                            pos.add(snapshot.getString("pos"));

                            if (names.size()==12){
                                createList(names,team,pos);
                            }

                        }
                    }
                }
            });
        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.confirm_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:

                documentReference = db.collection("user").document(uid);
                documentReference.update("teamlist", list ,
                        "team",true
                        ).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("write", "DocumentSnapshot successfully updated!");
                        startActivity(new Intent(Confirm.this,Team.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.d("write", "DocumentSnapshot failed");
                    }
                });

        }
        return super.onOptionsItemSelected(item);
    }

    void createList(ArrayList<String> names,ArrayList<String> pos,ArrayList<String> teams){
        ListView listView = findViewById(R.id.confirmlist);

        ConfirmData confirmData =new ConfirmData(Confirm.this,names,teams,pos);

        listView.setAdapter(confirmData);
    }
}
