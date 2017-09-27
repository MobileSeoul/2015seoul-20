package com.example.study.trash.Map;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MapConnector extends AsyncTask<String,Void,String>{
    URL url;
    HttpURLConnection con;
    BufferedReader buffr;
    StringBuffer sb;
    MyLocation myLocation;
    MapConnector mapConnector;

    public MapConnector(MyLocation myLocation) {
        mapConnector=this;
        this.myLocation = myLocation;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String... params) {
        connect(params[0]);
        MapPaser paser = new MapPaser();
        myLocation.list=paser.parseJson(sb);
        return null;
    }

    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    public void connect(String params) {
        try {
            url=new URL(params);
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setDoOutput(true);
            con.setDoInput(true);
            int code=con.getResponseCode();
            if(code==200) {
                buffr=new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String msg=null;
                sb=new StringBuffer();
                while((msg = buffr.readLine()) != null) {
                    sb.append(msg);
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(buffr!=null) {
                try {
                    buffr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(con!=null) {
                con.disconnect();
            }
        }
    }
}
