package com.example.register;

import android.content.Context;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;

public class MySQLClient {
    /*
    retrieve url
     */
    private static final String DATA_RETRIEVE_URL = "https://ramahprogrammer.000webhostapp.com//emoney/index.php";
    //instance field
    private final Context c;
    private SimpleTableDataAdapter adapter;

    public MySQLClient(Context c) {
        this.c = c;
    }

    //retrieve/select
    public void retrieve(final TableView tb) {
        final ArrayList<Spacecraft> spacecrafts = new ArrayList<>();
        AndroidNetworking.get(DATA_RETRIEVE_URL)
                .setPriority(Priority.HIGH)
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        JSONObject jo;
                        Spacecraft s;
                        try {
                            for (int i = 0; i < response.length(); i++)
                            {
                              jo=response.getJSONObject(i);
                              int id=jo.getInt("id");
                              String fromm=jo.getString("fromm");
                              String muda=jo.getString("muda");
                              String amount=jo.getString("amount");
                                String recieved_from=jo.getString("recieved_from");
                              s=new Spacecraft();
                              s.setId(id);
                              s.setFromm(fromm);
                              s.setMuda(muda);
                              s.setAmount(amount);
                              s.setRecieved_from(recieved_from);
//                              s.setEmp_id(emp_id);
//                              s.setFname(fname);
//                              s.setGender(gender);
//                              s.setDivision(division);
                              spacecrafts.add(s);
                            }
                            //set to tableview
                            adapter=new SimpleTableDataAdapter(c,new TableHelper(c).returnSpaceProbesArray(spacecrafts));
                            tb.setDataAdapter(adapter);
                        } catch (JSONException e) {
                            Toast.makeText(c,"good response but java cant pass json it received."+e.getMessage(), Toast.LENGTH_LONG).show();

                        }
                    }
                    @Override
                    public void onError(ANError anError) {
                        anError.printStackTrace();
                        Toast.makeText(c,"UNSUCCESSFUL : ERROR IS :"+anError.getMessage(), Toast.LENGTH_LONG).show();

                    }


                });
    }
}