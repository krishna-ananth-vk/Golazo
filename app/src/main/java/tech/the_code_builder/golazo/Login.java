package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    Button lgnbtn;
    TextView sgnup;
    EditText e,p;
    String email,password;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        firebaseAuth = FirebaseAuth.getInstance();

        lgnbtn = findViewById(R.id.lgnbtn);
        sgnup = findViewById(R.id.newuser);
        e = findViewById(R.id.email);
        p = findViewById(R.id.password);




        lgnbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                email = e.getText().toString();
                password = p.getText().toString();
                if (!email.isEmpty() || !password.isEmpty()){
                    lgnbtn.setText("Logging In");
                    firebaseAuth.signInWithEmailAndPassword(email,password)
                            .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){

                                        startActivity(new Intent(Login.this,Home.class ).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(Login.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                                        lgnbtn.setText("Login");
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Login.this,"please provide credentials "+email+" "+password,Toast.LENGTH_LONG).show();
                }
            }
        });

        sgnup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
            }
        });
    }



}
