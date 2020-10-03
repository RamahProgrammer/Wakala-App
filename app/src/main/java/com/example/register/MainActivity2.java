package com.example.register;


import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity2 extends AppCompatActivity {
    TableView<String[]> tb;
    TableHelper tableHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //tableview
        tableHelper = new TableHelper(this);
        tb = (TableView<String[]>) findViewById(R.id.TableView);
        tb.setColumnCount(4);
        tb.setHeaderBackgroundColor(Color.parseColor("white"));
        tb.setClickable(true);

        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {


            public void onClick(View view) {
                new MySQLClient(MainActivity2.this).retrieve(tb);
            }
        });
    }
}

