package com.wuxiantao.wxt.net.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GsonManager
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午5:57
 * Description:${DESCRIPTION} GsonManager
 */
public class GsonManager {


    private static volatile GsonManager instance;

    private Gson mGson;

    private GsonManager(){
        mGson = createGson();
    }

    public static GsonManager newInstance(){
        if (instance == null){
            synchronized (GsonManager.class){
                if (instance == null){
                    instance = new GsonManager();
                }
            }
        }
        return instance;
    }


    private Gson createGson(){
        return new GsonBuilder()
                .registerTypeAdapter(Integer.class,new IntegerDefaultAdapter())
                .registerTypeAdapter(String.class,new StringNullAdapter()).create();
    }

    public Gson getGson(){
        return mGson;
    }


    /**
     *   json 解析 成List
     * @param json json
     * @param clazz 类
     * @return list
     */
    public  <T> ArrayList<T> jsonToArrayList(String json, Class<T> clazz) {
        Type type = new TypeToken<ArrayList<JsonObject>>() {}.getType();
        ArrayList<JsonObject> jsonObjects = mGson.fromJson(json, type);
        ArrayList<T> arrayList = new ArrayList<>();
        for (JsonObject jsonObject : jsonObjects) {
            arrayList.add(mGson.fromJson(jsonObject, clazz));
        }
        return arrayList;
    }
}
