package com.abmcloud.cf.test.Utils;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Json {

    private JSONObject jsonObj;

    public Json(String fileName) {
        File file = new File(fileName);
        String jsonString = "";
        try {
            jsonString = FileUtils.readFileToString(file, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        jsonObj = new JSONObject(jsonString);
    }

    public JSONArray getJsonArray(String key) {
        return jsonObj.getJSONArray(key);
    }

    public List<Object> getList(String key) {
        return jsonObj.getJSONArray(key).toList();
    }

    public String getString(String key) {
        return jsonObj.getString(key);
    }
}
