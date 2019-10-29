package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {

    TextView lgnin,email,password;
    Button signup;
    String e,p;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);



        lgnin = findViewById(R.id.lgnin);
        signup = findViewById(R.id.signup);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();






        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                e = email.getText().toString().trim();
                p = password.getText().toString().trim();

                if (e.isEmpty() || p.isEmpty()){
                    Toast.makeText(Signup.this,"Please Provide Credentials",Toast.LENGTH_LONG);
                }
                else {
                    firebaseAuth.createUserWithEmailAndPassword(e,p)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(Signup.this,UserData.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        finish();
                                    }
                                    else{
                                        Toast.makeText(Signup.this,"Failed create an account. Please try again later",Toast.LENGTH_LONG);
                                    }
                                }
                            });
                }

            }
        });
        lgnin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));
                finish();
            }
        });


    }
}
