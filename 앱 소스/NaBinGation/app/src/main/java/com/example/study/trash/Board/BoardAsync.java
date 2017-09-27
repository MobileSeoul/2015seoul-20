package com.example.study.trash.Board;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;


import com.example.study.trash.Main.MainActivity;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class BoardAsync extends AsyncTask<String, Void, String> {
    URL url;
    HttpURLConnection con;
    BoardFragment boardFragment;
    StringBuffer sb;

    /* 쓰레드가 동작하기 전 초기화 등의 작업에 사용하면 용!!
    * 이 메서드는 메인 쓰레드에 의해!! (UI 제어 가능)
    * */

    public BoardAsync(BoardFragment boardFragment) {
        this.boardFragment = boardFragment;
    }

    protected void onPreExecute() {
        super.onPreExecute();
    }

    /* 하위 쓰레드에 의해 동작하는 메서드!!
     * 메인 쓰레드와는 독립적으로 수행할 영역은 이 메서드를 이용!!
     * UI 제어 불가!!
      * */
    protected String doInBackground(String[] params) {
        sb = new StringBuffer();

        try {
            url = new URL(params[0]);
            con = (HttpURLConnection) url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream(), "UTF-8"));
            String data = null;

            while ((data = bufferedReader.readLine()) != null) {

                sb.append(data);
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    /* 개발자가 정의한 쓰레드가 doInBackground() 메서드를 수행하는 동안
     * 중간 중간에 UI를 제어하는 등의 작업이 가능!!
     * 이 메서드는 메인 쓰레드에 의해 수행!!
      * */
    protected void onProgressUpdate(Void[] values) {
        super.onProgressUpdate(values);
    }

    /*  doInBackground() 메서드의 수행이 모두 완료되면, 최종적으로
     * 객체를 반환해주는 데, 그 객체를 전달 받아 UI등에 반영이 가능한 메서드!!
     * 이 메서드의 수행 주체는 메인 쓰레드!!
      * */
    protected void onPostExecute(String json) {

        BoardParser parser = new BoardParser();

        ArrayList<Board> list = parser.parseJson(json);
        Log.d("BoardAsync", boardFragment.list.size() + "");
        boardFragment.adapter = new BoardAdapter(boardFragment.getActivity(), list);
        boardFragment.listView.setAdapter(boardFragment.adapter);
    }
}
