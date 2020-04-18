package com.wuxiantao.wxt.ui.activity;

import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:IncomeLegendActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-11-7 下午1:26
 * Description:${DESCRIPTION} 收益说明
 */
@ContentView(R.layout.activity_income_legend)
public class IncomeLegendActivity extends BaseActivity {

    @ViewInject(R.id.income_legend_img)
    ImageView income_legend_img;

    @Override
    public void initView() {
        income_legend_img.setImageResource(R.mipmap.income_table);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.income_legend)
                .build();
    }
}
