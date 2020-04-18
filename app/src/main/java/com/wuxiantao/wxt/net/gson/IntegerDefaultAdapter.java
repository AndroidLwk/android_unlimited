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
 * FileName:IntegerDefaultAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 上午9:54
 * Description:${DESCRIPTION}
 */
public class IntegerDefaultAdapter implements JsonSerializer<Integer>, JsonDeserializer<Integer> {


    @Override
    public Integer deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        //定义为int类型,如果后台返回""或者null,则返回0
        try{
            if (json.getAsString().equals("") || json.getAsString().equals("null")){
                return 0;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            return json.getAsInt();
        } catch (NumberFormatException e){
            throw new JsonSyntaxException(e);
        }
    }

    @Override
    public JsonElement serialize(Integer src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src);
    }
}
