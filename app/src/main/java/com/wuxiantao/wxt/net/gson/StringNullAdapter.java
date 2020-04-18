package com.wuxiantao.wxt.net.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:StringNullAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 上午10:03
 * Description:${DESCRIPTION}
 */
public class StringNullAdapter extends TypeAdapter<String> {

    @Override
    public void write(JsonWriter out, String value) throws IOException {
        if (value == null){
            out.nullValue();
            return;
        }
        out.value(value);
    }

    @Override
    public String read(JsonReader in) throws IOException {
        if (in.peek() == JsonToken.NULL){
            in.nextNull();
            //原先是返回Null，这里改为返回空字符串
            return "";
        }
        String json = in.nextString();
        if (json.equals("null")){
            return "";
        }
        return json;
    }
}
