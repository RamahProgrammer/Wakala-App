package com.example.register;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.SmsMessage;
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

import static android.content.Context.MODE_PRIVATE;

public class MessageReceiver extends BroadcastReceiver {
    private RequestQueue rq;
    private StringRequest request;
    SharedPreferences loginpreferences;
    SharedPreferences.Editor loginprefeditor;
    private static final String URL = "https://ramahprogrammer.000webhostapp.com//emoney/register.php";
    String msg_body;
    String agnum;
    String msg_from;
   long current_time;
    private static final String TAG="Message Received";
    @Override
    public void onReceive(Context context, Intent intent) {
        loginpreferences=context.getSharedPreferences("loginprefs",MODE_PRIVATE);
        loginprefeditor=loginpreferences.edit();
        loginpreferences=context.getSharedPreferences("data", MODE_PRIVATE);
        agnum=loginpreferences.getString("number","");
        Toast.makeText(context, agnum, Toast.LENGTH_SHORT).show();
        rq = Volley.newRequestQueue(context);
        if(intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")){
            Bundle bundle=intent.getExtras();
            SmsMessage[] msg;
            if(bundle != null){
                try{
                    Object[] pdus=(Object[]) bundle.get("pdus");
                    msg=new SmsMessage[pdus.length];
                    for(int i=0;i<msg.length; i++){
                        msg[i]=SmsMessage.createFromPdu((byte[])pdus[i]);
                        msg_from=msg[i].getOriginatingAddress();
                        msg_body=msg[i].getMessageBody();
                         current_time=msg[i].getTimestampMillis();
                        if(msg[i].getOriginatingAddress().equals("M-PESA") || msg[i].getOriginatingAddress().equals("TIGOPESA") || msg[i].getOriginatingAddress().equals("T-PESA")|| msg[i].getOriginatingAddress().equals("+255674382638")) {
                            Intent smsintent=new Intent(context,message.class);
                            Toast.makeText(context, "Message from"+msg_from+"\n"+msg_body, Toast.LENGTH_SHORT).show();
                            Message();
                            smsintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            smsintent.putExtra("Message Number", msg_from);
                            smsintent.putExtra("Message", msg_body);
                            smsintent.putExtra("Time",current_time);
                            context.startActivity(smsintent);
                        }
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

    }
    public void Message(){
        request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jo = new JSONObject(response);
                    String success = jo.getString("success");
                    if (success.equals("1")) {
                        //  Toast.makeText(, "", Toast.LENGTH_SHORT).show();
                    }
                    else if (success.equals("0")) {

                    }
                } catch (JSONException e) {
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("message", msg_body);
                params.put("fromm", msg_from);
//                params.put("muda", String.valueOf(current_time));
                return params;
            }
        };
        rq.add(request);
    }
}