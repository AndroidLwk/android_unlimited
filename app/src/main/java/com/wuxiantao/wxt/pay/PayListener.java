package com.wuxiantao.wxt.pay;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/8/28 0028 18:38 8-19
 * Description: ${DESCRIPTION} 通过管理类管理接口
 * Author: Administrator Shiming-Shi
 */

public class PayListener {

    private static PayListener instance;

    private   List<PayResultListener> resultList;

    private PayListener(){
        resultList = new ArrayList<>();
    }

    public static PayListener getInstance(){
        if (instance==null){
            synchronized (PayListener.class){
                if (instance==null){
                    return instance = new PayListener();
                }
            }
        }
        return instance;
    }



    public  void addListener(PayResultListener listener){
        if (!resultList.contains(listener)){
            if (!resultList.isEmpty()){
                resultList.remove(listener);
                resultList.clear();
            }
            resultList.add(listener);
        }else {
            resultList.remove(listener);
            resultList.clear();
        }
    }


    public boolean  removeListener(PayResultListener listener){
         return resultList.remove(listener);
    }

    public void  addSuccess(){
        for (PayResultListener l : resultList){
             l.onPaySuccess();
        }
    }



    public void  addCancel(){
        for (PayResultListener l : resultList){
            l.onPayCancel();
        }
    }



    public void  addError(){
        for (PayResultListener l : resultList){
            l.onPayError();
        }
    }






}

