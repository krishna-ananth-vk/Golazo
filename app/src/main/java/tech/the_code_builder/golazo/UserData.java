package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class UserData extends AppCompatActivity {

    Spinner sem,dept;
    EditText name;
    Button save;
    FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
    FirebaseUser user;
    String uid;

    String s,d,n;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdata);

        sem = findViewById(R.id.sem);
        dept = findViewById(R.id.dept);

        name = findViewById(R.id.name);
        save = findViewById(R.id.save);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();






        ArrayAdapter<CharSequence> deptArray = ArrayAdapter.createFromResource(this,R.array.department,android.R.layout.simple_spinner_item);
        deptArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dept.setAdapter(deptArray);


        ArrayAdapter<CharSequence> semArray = ArrayAdapter.createFromResource(this,R.array.sem,android.R.layout.simple_spinner_item);
        semArray.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sem.setAdapter(semArray);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.savebtn:
//                startActivity(new Intent(this, Profile.class));
                n = name.getText().toString();
                s = sem.getSelectedItem().toString();
                d = dept.getSelectedItem().toString();
                DocumentReference documentReference = firebaseFirestore.collection("user").document(uid);
                documentReference.update("name",n,
                        "sem",s,
                        "dept",d
                ).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Log.d("write", "DocumentSnapshot successfully updated!");
                    startActivity(new Intent(UserData.this,Home.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                    finish();
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w("read", "Error updating document", e);
                        }
                    });

        }

        return super.onOptionsItemSelected(item);
    }
}
