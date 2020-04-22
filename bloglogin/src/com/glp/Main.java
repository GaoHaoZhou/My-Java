package com.glp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class Main {
    public static void main(String[] args) {
        JSONArray array = new JSONArray();

        JSONObject o1 = new JSONObject();
        o1.put("id", 1);
        o1.put("cover_image", "/images/1.png");
        o1.put("title", "title1");
        o1.put("body", "body1");

        array.add(o1);

        JSONObject o2 = new JSONObject();
        o2.put("id", 2);
        o2.put("cover_image", "/images/2.png");
        o2.put("title", "title2");
        o2.put("body", "body2");

        array.add(o2);

        System.out.println(array.toJSONString());
    }
}
