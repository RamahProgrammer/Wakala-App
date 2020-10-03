package com.example.register;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class welcome extends AppCompatActivity {
    TextView kuhusu;
    Button chomandani;
    TextView jisajili;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        jisajili=(TextView) findViewById(R.id.register);
        kuhusu=(TextView) findViewById(R.id.about);
        chomandani=(Button) findViewById(R.id.login);
        jisajili.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jis=new Intent(getApplicationContext(),register.class);
                startActivity(jis);
            }
        });
        kuhusu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jis=new Intent(getApplicationContext(),swiping.class);
                startActivity(jis);
            }
        });
        chomandani.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jisa=new Intent(getApplicationContext(),Login.class);
                startActivity(jisa);
                finish();
            }
        });
    }
}
