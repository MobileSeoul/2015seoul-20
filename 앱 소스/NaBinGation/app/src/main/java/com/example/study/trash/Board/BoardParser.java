package com.example.study.trash.Board;

import android.util.Log;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;


/**
 * Created by seungjong on 2015-10-26.
 */

public class BoardParser {

    public ArrayList<Board> parseJson(String sb) {
        JSONParser jsonParser = new JSONParser();
        JSONObject obj;
        ArrayList<Board> list = new ArrayList<Board>();
        try {
            JSONObject root = (JSONObject) jsonParser.parse(sb);
            JSONArray arr = (JSONArray) root.get("board");

            for (int i = 0; i < arr.size(); i++) {
                Board dto = new Board();
                obj = (JSONObject) jsonParser.parse(arr.get(i).toString());
                dto.setBoard_id(Integer.parseInt(obj.get("board_id").toString()));
                dto.setWriter((String) obj.get("writer"));
                dto.setTitle((String) obj.get("title"));
                dto.setContent((String) obj.get("content"));
                dto.setRegdate((String) obj.get("regdate"));
                dto.setStatus(Integer.parseInt(obj.get("status").toString()));
                dto.setLocation(Integer.parseInt(obj.get("location").toString()));
                list.add(dto);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}

