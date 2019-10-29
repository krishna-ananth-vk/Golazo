package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

public class Profile extends AppCompatActivity {
    FirebaseAuth firebaseAuth;

    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseUser user;
    TextView name,sem,dept,point;
    Button lgot;
    String uid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);


        name = findViewById(R.id.name);
        sem = findViewById(R.id.sem);
        dept = findViewById(R.id.dept);
        point = findViewById(R.id.point);
        lgot = findViewById(R.id.lgout);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        uid = user.getUid();

        lgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(Profile.this,Login.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();
            }
        });

        final DocumentReference documentReference =db.collection("user").document(uid);

        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    Log.d("Result","success");
                    DocumentSnapshot documentSnapshot =task.getResult();
                    if (documentSnapshot.exists()){
                        name.setText(documentSnapshot.getString("name"));
                        sem.setText(documentSnapshot.getString("sem"));
                        dept.setText(documentSnapshot.getString("dept"));
                        point.setText(documentSnapshot.getString("point"));
                    }
                    else {
                        Log.d("Result","fail");
                    }
                }
                else {
                    Log.d("Result","failed");
                }
            }
        });





    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:
//                startActivity(new Intent(this, Profile.class));

                startActivity(new Intent(this, UserData.class));


        }
        return super.onOptionsItemSelected(item);
    }
}
