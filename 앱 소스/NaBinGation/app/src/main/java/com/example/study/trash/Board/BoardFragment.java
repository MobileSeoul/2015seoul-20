package com.example.study.trash.Board;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.study.trash.Main.MainActivity;
import com.example.study.trash.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class BoardFragment extends Fragment implements View.OnClickListener {

    ListView listView;
    BoardAdapter adapter;
    Button bt_board_write;
    ArrayList<Board> list = new ArrayList<Board>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.board_layout, container, false);

        //Log.d("BoardFragment", list.size()+"");

        listView = (ListView) view.findViewById(R.id.listView);
        //adapter = new BoardAdapter(getActivity(), list);
        //listView.setAdapter(adapter);

        bt_board_write = (Button) view.findViewById(R.id.bt_board_write);
        bt_board_write.setOnClickListener(this);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Board board = adapter.getItem(position);
                board.setContent(adapter.getItem(position).getContent());
                Log.d("fragment content", board.getContent());
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra("BOARD", board);
                startActivity(i);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_board_write) {
            writeGo();
        }
    }

    public void writeGo() {
        Intent intent = new Intent(getActivity(), WriteActivity.class);
        startActivity(intent);
    }
/*
    public void setList(ArrayList<Board> list) {
        Log.d("BoardFragment", list.size() + "");
        this.list = list;
        Log.d("BoardFragment", this.list.size() + "");
    }
    */
}
