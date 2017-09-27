package com.example.study.trash.Board;


import android.os.AsyncTask;
import android.util.Log;

import com.example.study.trash.Main.MainActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WriteAsync extends AsyncTask<String, Void, String> {
    URL url;
    HttpURLConnection con;
    StringBuffer sb;
    WriteActivity writeActivity;
    String[] arr=new String[3];

    public WriteAsync(WriteActivity writeActivity, String[] arr) {
        this.writeActivity = writeActivity;
        this.arr = arr;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    protected String doInBackground(String[] params) {
        Log.d("array value 0", arr[0]);
        Log.d("array value 1", arr[1]);
        Log.d("array value 2", arr[2]);
        Log.d("array value 3", arr[3]);

        try {
            url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();

            con.setConnectTimeout(60000);
            con.setReadTimeout(60000);
            con.setRequestMethod("POST");
            con.setDoInput(true);
            con.setDoOutput(true);
            con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            String title = arr[0];
            String writer = arr[1];
            String content = arr[2];
            int location = Integer.parseInt(arr[3]);

            sb = new StringBuffer();
            sb.append("title").append("=").append(title).append("&");
            sb.append("writer").append("=").append(writer).append("&");
            sb.append("content").append("=").append(content).append("&");
            sb.append("location").append("=").append(location);

            OutputStream os = con.getOutputStream();

            os.write(sb.toString().getBytes());
            os.flush();
            os.close();

            int responseCode = con.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                InputStream is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                StringBuilder sbu = new StringBuilder();
                String line;
                while((line = br.readLine()) != null) {
                    sbu.append(line);
                    sbu.append("\n\r");
                }
                return sbu.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(Void[] values) {
        super.onProgressUpdate(values);
    }

    protected void onPostExecute(String result) {
        Log.d("WriteAsync", MainActivity.getMainActivity().toString());
        MainActivity.getMainActivity().sendListUrl();
    }
}
