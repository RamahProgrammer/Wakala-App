package com.example.register;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class report extends Activity {

    JSONParser jParser;
    String URL_TO_PHP = "http://192.168.43.242/pdf/index.php";
    String TAG_SUCCESS = "success";
    String TAG_STUFF = "stuff";
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
    }

  public void onClick(View v) {
     switch (v.getId()) {
           case R.id.report:
              new testing().execute();
               break;
         default:
             break;
     }

   }

//    public void navigate(View view) {
//        // retrieve the index of the user's choice on the number picker
//
//        webView.setWebViewClient(new WebViewClient());
//        // load the appropriate web page
//
//            webView.loadUrl(URL_TO_PHP);
//
//    }

    private class testing extends AsyncTask<Void, Void, Void> {

        @Override
     protected void onPreExecute() {
          super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... args) {

           /* Building Parameters */
            List<NameValuePair> params = new ArrayList<>();

            /* getting JSON string from URL */
           JSONObject json = jParser.makeHttpRequest(URL_TO_PHP, "GET", params);

          try {
                /* Checking for SUCCESS TAG */
                int success = json.getInt(TAG_SUCCESS);
                if (success == 1) {
                    JSONArray JAStuff = json.getJSONArray(TAG_STUFF);

                    /** CHECK THE NUMBER OF RECORDS **/
                    int intStuff = JAStuff.length();

                    if (intStuff != 0) {

                        for (int i = 0; i < JAStuff.length(); i++) {
                            JSONObject JOStuff = JAStuff.getJSONObject(i);
                            Log.e("ALL THE STUFF", JOStuff.toString());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
