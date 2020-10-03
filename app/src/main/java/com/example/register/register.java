package com.example.register;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class register extends AppCompatActivity {
Button nn;
    RequestQueue rq;
    StringRequest request;
    String register_url = "https://ramahprogrammer.000webhostapp.com//emoney/agents.php";
EditText name,number,floats,cash,pass,cpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nn=(Button) findViewById(R.id.register);
        name=findViewById(R.id.agname);
        number=findViewById(R.id.agnumber);
        floats=findViewById(R.id.ifloat);
        cash=findViewById(R.id.icash);
        pass=findViewById(R.id.password);
        cpass=findViewById(R.id.cpasswd);
        rq = Volley.newRequestQueue(this);
        nn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
if(name.getText().toString().equals("") || number.getText().toString().equals("") ||floats.getText().toString().equals("")||cash.getText().toString().equals("")|| pass.getText().toString().equals("")){
    Toast.makeText(register.this, "All Fields Must filled", Toast.LENGTH_SHORT).show();
}else if(!pass.getText().toString().equals(cpass.getText().toString())){
    Toast.makeText(register.this, "password and confirm password must be equal", Toast.LENGTH_SHORT).show();
}else{
    jisajili();
}
            }
        });
    }
    public void jisajili(){
        request=new StringRequest(Request.Method.POST, register_url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo=new JSONObject(response);
                    String fanikiwa=jo.getString("success");
                    if(fanikiwa.equals("1")){
                        Toast.makeText(getApplicationContext(),"Succesfful",Toast.LENGTH_LONG).show();
                        String namba=number.getText().toString();
                        Intent jis=new Intent(register.this,Login.class);
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
                Toast.makeText(register.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String,String> params=new HashMap<>();
                params.put("name",name.getText().toString());
                params.put("number",number.getText().toString());
                params.put("floats",floats.getText().toString());
                params.put("cash",cash.getText().toString());
                params.put("password",pass.getText().toString());

                return params;
            }
        };
        rq.add(request);
    }
    }
