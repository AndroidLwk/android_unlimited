package com.wuxiantao.wxt.ui.activity;

import android.graphics.Color;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyTeamActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-4 下午7:29
 * Description:${DESCRIPTION} 我的团队
 */
@ContentView(R.layout.activity_my_team)
public class MyTeamActivity extends BaseActivity {


    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.friend_rule, Color.BLACK).build();
    }
}
