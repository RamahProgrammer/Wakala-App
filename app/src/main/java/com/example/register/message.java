package com.example.register;

import android.Manifest;

import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import java.util.Map;


public class message extends AppCompatActivity{
    private Handler mHandler;
    TextView ujum,sim;
    private StringRequest request;
    private RequestQueue rq;
    ArrayList<messageModel> messageModelArrayList =new ArrayList<messageModel>();
    private static final String allmessages = "https://ramahprogrammer.000webhostapp.com//emoney/daktariall.php";
    MessageAdapter  messageAdapter;
    RecyclerView recyclerView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
                this.mHandler = new Handler();
        this.mHandler.postDelayed(m_Runnable,0);
        Bundle extras=getIntent().getExtras();
if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS)
!= PackageManager.PERMISSION_GRANTED){
    requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1000);
}
//MessageReceiver.bindListener(this);
//       put();
ujum=findViewById(R.id.messagee);
sim=findViewById(R.id.phone);

        recyclerView=(RecyclerView) findViewById(R.id.recyclerview);
        messageAdapter=new MessageAdapter(this,messageModelArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
        GetData();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1000){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Permission granted", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Permission not granted", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }
    private final Runnable m_Runnable = new Runnable(){public void run() {
       // GetData();
      //put();
        //message.this.mHandler.postDelayed(m_Runnable, 0);
    }
    };
    public void put() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String add = extras.getString("Message Number");
            String mess = extras.getString("Message");

            ujum.setText(mess);
            sim.setText(add);

      }
    }
private void GetData(){
    StringRequest stringRequest=new StringRequest(Request.Method.POST, allmessages, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            try {
                messageModelArrayList.clear();
                JSONArray jsonArray=new JSONArray(response);
                for(int i=0;i<jsonArray.length();i++){
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    messageModel messageModel=new messageModel();
                    messageModel.setSms(jsonObject.getString("message"));
                    messageModel.setFrom(jsonObject.getString("fromm"));

                    messageModelArrayList.add(messageModel);
                }
                messageAdapter.notifyDataSetChanged();

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(getApplicationContext(),error.toString(), Toast.LENGTH_SHORT).show();
        }
    }) {
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String, String> params=new HashMap<>();
            return params;
        }
    };
    RequestQueue requestQueue= Volley.newRequestQueue(this);
    requestQueue.add(stringRequest);
}

    }

