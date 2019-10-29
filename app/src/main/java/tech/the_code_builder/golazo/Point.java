package tech.the_code_builder.golazo;

import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Point extends AppCompatActivity {

    ActionBar actionBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.point);

        actionBar = getSupportActionBar();
        actionBar.setTitle("Point Table");
    }
}
