package com.example.register;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class Home extends AppCompatActivity {
    Button messageinn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        messageinn=(Button) findViewById(R.id.message);
        messageinn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent jis=new Intent(getApplicationContext(),MessageReceiver.class);
                startActivity(jis);

            }
        });
    }
}
