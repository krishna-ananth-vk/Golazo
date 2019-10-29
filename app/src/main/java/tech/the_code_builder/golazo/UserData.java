package tech.the_code_builder.golazo;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserData extends AppCompatActivity {

    Spinner sem,dept;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userdata);

        sem = findViewById(R.id.sem);
        dept = findViewById(R.id.dept);


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

                startActivity(new Intent(this, Profile.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
                finish();

        }
        return super.onOptionsItemSelected(item);
    }
}
