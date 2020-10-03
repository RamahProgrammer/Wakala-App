package com.example.register;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {
    TextView jisajili,kuhusu;
    Button chomandani;
    EditText number,passwd;
    RequestQueue rq;
    SharedPreferences loginpreferences;
    SharedPreferences.Editor loginprefeditor;
    StringRequest request;
    String login_url = "https://ramahprogrammer.000webhostapp.com//emoney/login.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        jisajili=(TextView) findViewById(R.id.register);
        kuhusu=(TextView) findViewById(R.id.about);
        number=findViewById(R.id.agnumber);
        passwd=findViewById(R.id.password);
        chomandani=(Button) findViewById(R.id.login);
        rq = Volley.newRequestQueue(this);
        loginpreferences=getSharedPreferences("loginprefs",MODE_PRIVATE);
        loginprefeditor=loginpreferences.edit();
        number.setText(loginpreferences.getString("number",""));
        passwd.setText(loginpreferences.getString("password",""));
        SharedPreferences sharedPreferences=getSharedPreferences("data",MODE_PRIVATE);
        final SharedPreferences.Editor prefeditor=sharedPreferences.edit();
        prefeditor.putInt("isLogged",1);
        prefeditor.commit();
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
            @SuppressLint("ApplySharedPref")
            @Override
            public void onClick(View v) {
                if( number.getText().toString().equals("") || passwd.getText().toString().equals("")){
                    Toast.makeText(Login.this, "Must Be filled", Toast.LENGTH_SHORT).show();
                }else{
                    loginprefeditor.putString("number",number.getText().toString());
                    loginprefeditor.putString("password",passwd.getText().toString());
                    prefeditor.putInt("isLogged",1);
                    prefeditor.commit();
                    loginprefeditor.commit();
                    LOGIN();
                }

            }
        });
    }
    public void LOGIN(){
        request=new StringRequest(Request.Method.POST, login_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo=new JSONObject(response);
                    String fanikiwa=jo.getString("success");
                    if(fanikiwa.equals("1")){
                        Toast.makeText(getApplicationContext(),"Succesfful",Toast.LENGTH_LONG).show();
                        String namba=number.getText().toString();
                        Intent jis=new Intent(getApplicationContext(),Pembeni.class);
                        jis.putExtra("AGENT",namba);
                        startActivity(jis);
                        finish();
                    }
                    else if(fanikiwa.equals("0")){
                        Toast.makeText(getApplicationContext(),"UnSuccesfful",Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("number",number.getText().toString());
                params.put("password",passwd.getText().toString());
                return params;
            }
        };
        rq.add(request);
    }
}
