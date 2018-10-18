package com.example.demo.Tools;

import com.example.demo.Model.ResponseInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonTool {

    ///序列化结果。
    public static String SerializeObject(ResponseInfo data) {
        JSONObject json = JSONObject.fromObject(data);
        return json.toString();
    }

    ///序列反化结果。
    public static ResponseInfo DeserializeObject(String data) {
        JSONObject jsonObject = JSONObject.fromObject(data);
        ResponseInfo responseInfo = (ResponseInfo) JSONObject.toBean(jsonObject, ResponseInfo.class);
        return responseInfo;
    }


}
