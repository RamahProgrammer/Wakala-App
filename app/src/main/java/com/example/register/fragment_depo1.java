package com.example.register;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;

import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class fragment_depo1 extends Fragment
{

    private Handler mHandler;
    TextView ujum,sim;
    private StringRequest request;
    private RequestQueue rq;
    ArrayList<messageModel> messageModelArrayList =new ArrayList<messageModel>();
    private static final String allmessages = "https://ramahprogrammer.000webhostapp.com///emoney/deposit.php";
    MessageAdapter  messageAdapter;
    RecyclerView recyclerView;




    public fragment_depo1() {
    }

    View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        view=inflater.inflate(R.layout.depo1_fragment, container, false);
        return  view;

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.messagee);
        view.findViewById(R.id.phone);
        recyclerView=view.findViewById(R.id.recyclerview);
        messageAdapter=new MessageAdapter(getActivity(),messageModelArrayList);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(messageAdapter);
        GetData();



        this.mHandler = new Handler();
        Runnable m_Runnable = null;
        this.mHandler.postDelayed(m_Runnable,0);
        Bundle extras=getActivity().getIntent().getExtras();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED)
        {

            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS},1000);

        }





    }


    private void GetData()
    {

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
                Toast.makeText(getActivity(),error.toString(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params=new HashMap<>();
                return params;
            }
        };
        RequestQueue requestQueue= Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }





}
