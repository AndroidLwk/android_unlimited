package com.wuxiantao.wxt.ui.activity;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RuleActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-14 下午3:46
 * Description:${DESCRIPTION} 取钱规则
 */
@ContentView(R.layout.activity_rule)
public class RuleActivity extends BaseActivity {

    @ViewInject(R.id.rule_rl)
    SmartRefreshLayout rule_rl;

    @Override
    public void initView() {
        rule_rl.setEnableRefresh(false);
        rule_rl.setEnableLoadMore(false);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText("取钱规则")
                .build();
    }
}
