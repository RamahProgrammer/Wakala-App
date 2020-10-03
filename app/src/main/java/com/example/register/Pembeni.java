package com.example.register;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;

import androidx.appcompat.app.AlertDialog;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;


import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageButton;
import android.widget.TextView;

public class Pembeni extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    AlertDialog.Builder builder;
    TextView namba,jina;
private ImageButton muamala,ujumbe,repo;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pembeni);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        repo=findViewById(R.id.report);
        muamala=findViewById(R.id.transaction);
        ujumbe=findViewById(R.id.messagge);
       namba=findViewById(R.id.agnum);


       namba.setText(getIntent().getStringExtra("AGENT"));
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        muamala.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mm=new Intent(getApplicationContext(),MainActivity2.class);
                startActivity(mm);
            }
        });
        repo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent m=new Intent(getApplicationContext(),PdfCreationActivity.class);
                startActivity(m);
            }
        });
        ujumbe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), depo.class);
                startActivity(intent);
            }
        });
        builder = new AlertDialog.Builder(this);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_pembeni_drawer, menu);
        return true;
    }

    //@Override
 //   public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
    //    int id = item.getItemId();

        //noinspection SimplifiableIfStatement
     //   if (id == R.id.action_settings) {
      //      return true;
      //  }
//
     //   return super.onOptionsItemSelected(item);
   // }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_share) {
            try {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("text/plain");
                shareIntent.putExtra(Intent.EXTRA_SUBJECT, "WakalaApp");
                String shareMessage= "\nshare WakalaApp kwa marafiki ili nao wajipatie huduma sabiti " +
                        "" +
                        "\n\n";
                shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID +"\n\n";
                shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                startActivity(Intent.createChooser(shareIntent, "choose one"));
            } catch(Exception e) {
                //e.toString();
            }
        }else if (id==R.id.db){
            Intent emoney = new Intent(getApplicationContext(), About.class);
            startActivity(emoney);
//        }
//        else if (id == R.id.set) {
//            Intent emoney = new Intent(getApplicationContext(), Setting.class);
//            startActivity(emoney);
        } else if (id == R.id.help) {
            Intent emoney = new Intent(getApplicationContext(), help.class);
            startActivity(emoney);
        } else if (id == R.id.out) {
            builder.setMessage("Do you want to LogOUT ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            finish();
                            Intent nn = new Intent(getApplicationContext(), Login.class);
                            startActivity(nn);
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }else if (id == R.id.privacy) {
            Intent emoney = new Intent(getApplicationContext(), privacy.class);
            startActivity(emoney);
        }
        //DrawerLayout drawer = (DrawerLayout) findViewById(R.id.myDrawer);
       // drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
