package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Team extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseUser user;
    String uid;
    ActionBar actionBar;
    Boolean team;
    ArrayList<String> names,teams,pos,points;
    DocumentReference documentReference;
    ArrayList<String> players;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team);
        players = new ArrayList<>();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Your Team");

        names = new ArrayList<>();
        teams = new ArrayList<>();
        pos = new ArrayList<>();
        points = new ArrayList<>();

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        documentReference = firebaseFirestore.collection("user").document(uid);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot documentSnapshot = task.getResult();
                    if (documentSnapshot.exists()){
                        team = documentSnapshot.getBoolean("team");
                        teamView(team);
                        System.out.println("------------"+team);
                    }
                }


            }
        });
    }




    public void teamView(Boolean team){
        System.out.println("------------"+team);
        if (!team){
            TextView msg = findViewById(R.id.msg);
            msg.setVisibility(View.VISIBLE);
            Button create = findViewById(R.id.create);
            create.setVisibility(View.VISIBLE);
            create.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Team.this,CreateTeam.class));
                }
            });

        }
        else{



            documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()){
                        DocumentSnapshot documentSnapshot = task.getResult();
                        if (documentSnapshot.exists()){
                            System.out.println("------------------------------------");
                            players = (ArrayList<String>)  documentSnapshot.get("teamlist");
                            createlist(players);
                        }
                    }
                }
            });



        }
    }

    void createlist(ArrayList<String> list){
        System.out.println(players);

        if (!players.isEmpty()){
            System.out.println(players);

            for(String item: players){
                documentReference = firebaseFirestore.collection("players").document(item);

                documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if(task.isSuccessful()){
                            DocumentSnapshot snapshot = task.getResult();
                            if(snapshot.exists()){
                                names.add(snapshot.getString("name"));
                                pos.add(snapshot.getString("pos"));
                                teams.add(snapshot.getString("team"));
                                points.add(String.valueOf(snapshot.getLong("point")));
                                if (names.size()==9){
                                    list(names,pos,teams,points);
                                }
                            }
                            else {

                            }
                        }
                    }
                });
            }




        }

    }

    void list(ArrayList<String> names,ArrayList<String> pos,ArrayList<String> teams,ArrayList<String> points){
        System.out.println(names);
        ListView listView = findViewById(R.id.teamList);
        listView.setVisibility(View.VISIBLE);
        TeamItem teamItem = new TeamItem(Team.this,names,teams,pos,points);

        listView.setAdapter(teamItem);
    }
}

