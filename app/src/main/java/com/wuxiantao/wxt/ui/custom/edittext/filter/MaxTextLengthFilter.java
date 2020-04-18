package com.wuxiantao.wxt.ui.custom.edittext.filter;

import android.text.InputFilter;
import android.text.Spanned;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.app.BaseApplication;
import com.wuxiantao.wxt.utils.ToastUtils;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/9/5 0005 10:28 8-19
 * Description: ${DESCRIPTION} 输入框字符数量限制
 * Author: Administrator Shiming-Shi
 */

public class MaxTextLengthFilter implements InputFilter{

    private int mMaxLength;

    private String type;

    public MaxTextLengthFilter(int max,String type){
        this.mMaxLength = max;
        this.type = type;
    }

    public MaxTextLengthFilter(int max){
        this(max,"");
    }

    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart , int dend){
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if(keep < (end - start)){
            if (type.equals("input")){
                ToastUtils.warning(BaseApplication.getAppContext().getString(R.string.character_sum) + mMaxLength +
                        BaseApplication.getAppContext().getString(R.string.count));
            }
        }
        if(keep <= 0){
            return "";
        }else if(keep >= end - start){
            return null;
        }else{
            return source.subSequence(start,start + keep);
        }
    }







}
