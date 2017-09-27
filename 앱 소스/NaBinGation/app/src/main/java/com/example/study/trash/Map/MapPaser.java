package com.example.study.trash.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;

public class MapPaser {
    public ArrayList<BinMap> parseJson(StringBuffer sb) {
        JSONParser jsonParser = new JSONParser();
        JSONObject obj;
        ArrayList<BinMap> list = new ArrayList<BinMap>();
        try {
            JSONObject root = (JSONObject) jsonParser.parse(sb.toString());
            JSONArray arr = (JSONArray) root.get("trashbin");
            for (int i = 0; i < arr.size(); i++) {
                BinMap dto = new BinMap();
                obj = (JSONObject) jsonParser.parse(arr.get(i).toString());
                dto.setBin_id((String) obj.get("bin_id"));
                dto.setGu((String) obj.get("gu"));
                dto.setLatitude((String) obj.get("latitude"));
                dto.setLongitude((String) obj.get("longitude"));
                dto.setStatus((String) obj.get("status"));
                dto.setRecycle((String) obj.get("recycle"));
                list.add(dto);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return list;
    }
}
