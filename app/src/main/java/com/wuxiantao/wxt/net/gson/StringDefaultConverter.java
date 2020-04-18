package com.wuxiantao.wxt.net.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:StringDefaultConverter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 上午9:59
 * Description:${DESCRIPTION}对返回值为空处理
 */
public class StringDefaultConverter implements JsonSerializer<String>, JsonDeserializer<String> {


    @Override
    public String deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        try {
            if (json.getAsString().equals("null")){
                return "";
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            return json.getAsJsonPrimitive().getAsString();
        }catch (NumberFormatException e){
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(String src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
