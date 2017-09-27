package com.example.study.trash.Board;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.study.trash.Main.MainActivity;

import java.util.ArrayList;

/**
 * Created by KwakSeYoun on 2015. 10. 25..
 */
public class BoardAdapter extends BaseAdapter {
    ArrayList<Board> items = new ArrayList<Board>();
    Context mContext;

    public BoardAdapter(Context mContext, ArrayList<Board> items) {
        this.mContext = mContext;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Board getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        BoardView view;
        if(convertView == null){
            view = new BoardView(mContext);
        }else{
            view = (BoardView)convertView;
        }
        Board board = getItem(position);
        view.setData(board);
        return view;
    }
}
