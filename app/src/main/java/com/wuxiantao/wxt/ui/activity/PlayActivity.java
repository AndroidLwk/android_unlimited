package com.wuxiantao.wxt.ui.activity;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:PlayActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午6:22
 * Description:${DESCRIPTION} 玩法
 */
@ContentView(R.layout.activity_play)
public class PlayActivity extends BaseActivity {

    @Override
    public void initView() {

    }


    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.play)
                .build();
    }
}
