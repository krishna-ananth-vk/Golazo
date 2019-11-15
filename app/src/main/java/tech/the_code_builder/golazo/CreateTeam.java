package tech.the_code_builder.golazo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;

public class CreateTeam extends AppCompatActivity {

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private ArrayList<String> team = new ArrayList<>();
    private ArrayList<String> pos = new ArrayList<>();
    private ArrayList<String> list = new ArrayList<>();


    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    String uid = user.getUid();
    String d;
    int ce,cse,ec,ee,mech,barch,gk,f,m,b;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createteam);


        firebaseFirestore.collection("players").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    for (QueryDocumentSnapshot player: task.getResult()){
                        Log.d("player data", player.getId() + " => " + player.getData());

                        names.add(player.getString("name"));
                        pos.add(player.getString("pos"));
                        id.add(player.getId());
                        team.add(player.getString("team"));


                    }

                    final ListView listView = findViewById(R.id.playerlist);
                    Player player = new Player(CreateTeam.this,names,team,pos,id,list);
                    listView.setAdapter(player);


                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            TextView select = view.findViewById(R.id.select);
                            d = pos.get(i);
                            Log.d("msg", d);

                            if (list.contains(d)){
                                select.setText("Add");

                                list.remove(d);
                                removePlayer(team.get(i),pos.get(i));
                                Log.d("msg removed", d);
                            }
                            else {
                                if(list.size()<9){
                                    switch (team.get(i)) {

                                        case "b":
                                            Log.d("Team count",""+cse);
                                            if (b < 3) {
                                                cse++;
                                                list.add(d);
                                                select.setText("Remove");
                                            }
                                            else {
                                                Toast.makeText(CreateTeam.this,
                                                        "Player limit exceeded.  :(",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            break;
                                        case "m":
                                            Log.d("Team count",""+ce);
                                            if (m < 3) {
                                                m++;
                                                list.add(d);
                                                select.setText("Remove");
                                            }
                                            else {
                                                Toast.makeText(CreateTeam.this,
                                                        "Player limit exceeded.  :(",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            break;
                                        case "f":
                                            Log.d("Team count",""+ec);
                                            if (f < 2) {
                                                f++;
                                                list.add(d);
                                                select.setText("Remove");
                                            }
                                            else {
                                                Toast.makeText(CreateTeam.this,
                                                        "Player limit exceeded.  :(",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            break;
                                        case "gk":
                                            Log.d("Team count",""+ee);
                                            if (gk < 3) {
                                                gk++;
                                                list.add(d);
                                                select.setText("Remove");
                                            }
                                            else {
                                                Toast.makeText(CreateTeam.this,
                                                        "Player limit exceeded.  :(",
                                                        Toast.LENGTH_LONG).show();
                                            }

                                            break;

                                    }



                                }

                                else{
                                    Toast.makeText(CreateTeam.this,"Player limit exceeded maximum number is 12",Toast.LENGTH_LONG).show();
                                }

                            }

                        }
                    });


                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_team, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.preview:
                if(list.size()==12){
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            CreateTeam.this);
                    builder.setTitle("All set");
                    builder.setCancelable(false);
                    builder.setMessage("You have selected 12 players. Click okay to continue. Click cancel to make changes");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Intent i = new Intent(CreateTeam.this,Confirm.class);
                                    i.putExtra("list",list);
                                    startActivity(i);
                                }
                            });
                    builder.setNegativeButton("Cancel",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    Toast.makeText(CreateTeam.this,"Cancel is clicked",Toast.LENGTH_LONG).show();
                                }
                            });
                    builder.show();
                }
                else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            CreateTeam.this);
                    builder.setTitle("Oops..!");
                    builder.setCancelable(false);
                    builder.setMessage("Please select 12 players");
                    builder.setPositiveButton("OK",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {

                                }
                            });

                    builder.show();
                }

        }
        return super.onOptionsItemSelected(item);
    }

    public boolean checkteam(String team){
        Log.d("Team",""+team);
        switch (team) {

            case "cse":
                Log.d("Team count",""+cse);
                if (cse == 3) {
                    return false;
                }
                cse++;
                break;
            case "civil":
                Log.d("Team count",""+ce);
                if (ce == 3) {
                    return false;
                }
                ce++;
                break;
            case "ece":
                Log.d("Team count",""+ec);
                if (ec == 3) {
                    return false;
                }
                ec++;
                break;
            case "eee":
                Log.d("Team count",""+ee);
                if (ee == 3) {
                    return false;
                }
                ee++;
                break;
            case "mech":
                Log.d("Team count",""+mech);
                if (mech == 3) {
                    return false;
                }
                mech++;
                break;
            case "barch":
                Log.d("Team count",""+barch);
                if (barch == 3) {
                    return false;
                }
                barch++;
                break;
        }
        return true;
    }

    public boolean checkpos(String pos){
        switch (pos) {
            case "b":
                if (b == 3) {
                    return false;
                }
                b++;
                break;
            case "f":
                if (f == 3) {
                    return false;
                }
                f++;
                break;
            case "m":
                if (m == 3) {
                    return false;
                }
                m++;
                break;
            case "gk":
                if (gk == 3) {
                    return false;
                }
                gk++;
                break;
        }

        return true;
    }

    public void removePlayer(String team,String pos){

        switch (team) {
            case "cse":
                cse--;
                break;
            case "civil":
                ce--;
                break;
            case "ece":
                ec--;
                break;
            case "eee":
                ee--;
                break;
            case "mech":
                mech--;
                break;
            case "barch":
                barch--;
                break;
        }

        switch (pos) {
            case "b":
                b--;
                break;
            case "f":
                f--;
                break;
            case "m":
                m--;
                break;
            case "gk":
                gk--;
                break;
        }

    }
}
