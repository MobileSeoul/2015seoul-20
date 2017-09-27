package com.example.study.trash.Board;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.study.trash.R;

public class WriteActivity extends Activity implements View.OnClickListener {

    Button bt_cancel;
    Button bt_regist;
    Spinner spinner;
    ArrayAdapter<String> spinnerAdapter;
    EditText edit_title;
    EditText edit_writer;
    EditText edit_content;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);

        edit_title = (EditText) findViewById(R.id.edit_title);
        edit_writer = (EditText) findViewById(R.id.edit_writer);
        edit_content = (EditText) findViewById(R.id.edit_content);

        spinner = (Spinner) findViewById(R.id.spinner);

        bt_regist = (Button)findViewById(R.id.bt_regist);
        bt_regist.setOnClickListener(this);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_cancel.setOnClickListener(this);

        spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                (String[]) getResources().getStringArray(R.array.gu_list));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinner.setAdapter(spinnerAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_regist) {

            String title = edit_title.getText().toString();
            String writer = edit_writer.getText().toString();
            String content = edit_content.getText().toString();
            int selected = spinner.getSelectedItemPosition();

            if(title.length() == 0 || writer.length() == 0 || content.length() == 0){
                Toast.makeText(this, "내용을 제대로 입력하세요!!", Toast.LENGTH_SHORT).show();
            }else{
                String arr[] = {title, writer, content, Integer.toString(selected)};
                WriteAsync writeAsync = new WriteAsync(this, arr);
                writeAsync.execute("http://yellowpee.cafe24.com/board/insert.jsp");

                finish();
            }
        }

        if (v.getId() == R.id.bt_cancel) {
            write_back();
        }
    }

    public void write_back() {
        finish();
    }
}
