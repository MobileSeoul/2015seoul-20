package com.example.study.trash.Board;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.study.trash.Main.GuMapping;
import com.example.study.trash.R;

public class DetailActivity extends Activity{
    TextView  title, writer, date,  checked, content, location;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView)findViewById(R.id.txt_detail_title);
        writer = (TextView) findViewById(R.id.txt_detail_writer);
        date = (TextView) findViewById(R.id.txt_detail_date);
        checked =(TextView) findViewById(R.id.txt_detail_checked);
        content = (TextView) findViewById(R.id.txt_detail_content);
        location = (TextView) findViewById(R.id.txt_detail_location);

        Board board = getIntent().getParcelableExtra("BOARD");
        Log.d("board", board+"");
        Log.d("board id ", board.getBoard_id()+"");
        Log.d("writer", board.getWriter()+"");
        Log.d("title", board.getTitle()+"");
        Log.d("date", board.getRegdate()+"");
        Log.d("checked", board.getStatus()+"");
        Log.d("content", board.getContent()+"");
        Log.d("gu", board.getLocation()+"");

        setData(board);

    }

    private void setData(Board board) {

        String status = null;

        if(board.getStatus() == 0){
            status = "읽지않음";
        }else{
            status = "읽음";
        }

        writer.setText(board.getWriter());
        title.setText(board.getTitle());
        date.setText(board.getRegdate());
        content.setText(board.getContent());
        checked.setText(status);
        location.setText(GuMapping.getGuName(board.getLocation()));
    }

}
