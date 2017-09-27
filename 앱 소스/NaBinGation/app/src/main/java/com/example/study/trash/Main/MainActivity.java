package com.example.study.trash.Main;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.study.trash.Board.BoardAsync;
import com.example.study.trash.Board.BoardFragment;
import com.example.study.trash.R;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    ViewPager viewPager;
    MainAdapter adapter;
    //ArrayList<Board> datas = new ArrayList<Board>();
    ImageButton bt_map;
    ImageButton bt_board;

    public static MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt_map = (ImageButton) findViewById(R.id.bt_map);
        bt_board = (ImageButton) findViewById(R.id.bt_board);

        viewPager = (ViewPager) findViewById(R.id.viewPager);
        adapter = new MainAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);

        bt_map.setOnClickListener(this);
        bt_board.setOnClickListener(this);

        setMainActivity(this);
        sendListUrl();
        startActivity(new Intent(this, LoadingActivity.class));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 0) {
                    onMap();
                    getActionBar().setTitle("지도");
                } else {
                    onBoard();
                    getActionBar().setTitle("게시판");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void sendListUrl() {
        BoardAsync boardAsync = new BoardAsync((BoardFragment) adapter.getItem(1));
        boardAsync.execute("http://yellowpee.cafe24.com/board/list.jsp");
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_map) {
            viewPager.setCurrentItem(0, true);
            onMap();
        }
        if (v.getId() == R.id.bt_board) {
            viewPager.setCurrentItem(1, true);
            onBoard();
        }
    }

    public void onMap(){
        bt_map.setImageResource(R.drawable.clickmap);
        bt_board.setImageResource(R.drawable.unclickboard);
        bt_map.setBackgroundColor(Color.parseColor("#11000000"));
        bt_board.setBackgroundColor(Color.WHITE);
    }

    public void onBoard(){
        bt_map.setImageResource(R.drawable.unclickmap);
        bt_board.setImageResource(R.drawable.clickboard);
        bt_map.setBackgroundColor(Color.WHITE);
        bt_board.setBackgroundColor(Color.parseColor("#11000000"));
    }

    public static void setMainActivity(MainActivity mainActivity) {
        MainActivity.mainActivity = mainActivity;
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }
}
