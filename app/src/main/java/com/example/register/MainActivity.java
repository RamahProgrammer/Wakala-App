package com.example.register;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
TextView jisajili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jisajili=(TextView) findViewById(R.id.register);

        jisajili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jis=new Intent(getApplicationContext(),register.class);
                startActivity(jis);
            }
        });
    }
}
