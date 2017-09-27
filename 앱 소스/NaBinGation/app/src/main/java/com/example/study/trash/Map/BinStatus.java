package com.example.study.trash.Map;
;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class BinStatus extends AsyncTask<String,Void,Void>{
    URL url;
    HttpURLConnection con;

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected Void doInBackground(String... params) {
        try {
            url=new URL(params[0]);
            con=(HttpURLConnection)url.openConnection();
            int code=con.getResponseCode();
            Log.d(getClass().getName(), "code : "+code);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
    }
}
