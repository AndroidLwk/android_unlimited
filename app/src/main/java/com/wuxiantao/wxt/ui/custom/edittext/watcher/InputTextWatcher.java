package com.wuxiantao.wxt.ui.custom.edittext.watcher;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.TextView;

/**
 * Copyright (C), 2018-2019, 都可信有限公司
 * Date: 2019/4/25 0025 9:02 8-19
 * Description: ${DESCRIPTION}
 * Author: Administrator Shiming-Shi
 */

public class InputTextWatcher implements TextWatcher {

    private int max = 0;

    private TextView tv;

    public InputTextWatcher(int max,TextView tv){
        this.max = max;
        this.tv = tv;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String content = s.toString();
        int len = content.length();
        int current = max - len;
        tv.setText(current+"");
    }
}
