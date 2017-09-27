package com.example.study.trash.Board;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.study.trash.Main.GuMapping;
import com.example.study.trash.R;

/**
 * Created by KwakSeYoun on 2015. 10. 25..
 */
public class BoardView extends FrameLayout{


    TextView txt_writer;
    TextView txt_title;
    TextView txt_regdate;
    TextView txt_status;
    TextView txt_location;

    public BoardView(Context context) {
        super(context);
        init();
    }

    public BoardView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.item_layout, this);
        txt_writer = (TextView)findViewById(R.id.txt_writer);
        txt_title = (TextView)findViewById(R.id.txt_title);
        txt_regdate = (TextView)findViewById(R.id.txt_regdate);
        txt_status = (TextView)findViewById(R.id.txt_status);
        txt_location = (TextView)findViewById(R.id.txt_location);
    }

    public void setData(Board board){
        txt_writer.setText(board.getWriter());
        txt_title.setText(board.getTitle());
        txt_regdate.setText(board.getRegdate().substring(0, 10));

        String status=null;
        if(board.getStatus() == 1){
            status ="확인";
        }else{
            status = "확인안함";
        }
        txt_status.setText(status);
        txt_location.setText(GuMapping.getGuName(board.getLocation()));
    }


}
