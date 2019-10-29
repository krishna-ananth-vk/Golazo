package tech.the_code_builder.golazo;

import android.os.Bundle;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Leader extends AppCompatActivity {
    ActionBar actionBar;


    ArrayList<User> userData;
    private ListView leaderList;
    UserAdapter userAdapter;
    int i,j;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.leader);
        userData = new ArrayList<>();


        FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
        actionBar = getSupportActionBar();
        actionBar.setTitle("Your Team");

        firebaseFirestore.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    for (QueryDocumentSnapshot user: task.getResult()){
                        userData.add(new User(
                                user.getString("name"),
                                user.getString("point"),
                                user.getId(),
                                user.getString("pos")
                        ));

                        System.out.println(userData);


                    }

                    createList(userData);

                }
            }
        });







    }
    void createList(ArrayList<User> userData){

//        System.out.println(userData);
        Collections.sort(userData,new CustomComparator());

        leaderList = findViewById(R.id.leaderList);


        userAdapter =  new UserAdapter(userData,getApplicationContext());

        leaderList.setAdapter(userAdapter);
    }
}
