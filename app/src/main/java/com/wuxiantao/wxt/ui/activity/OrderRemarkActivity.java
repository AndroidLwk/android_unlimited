package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.title.TitleBuilder;
import com.wuxiantao.wxt.utils.StatusBarUtil;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import static com.wuxiantao.wxt.config.Constant.ORDER_REMARK;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_ORDER_REMARK;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:OrderRemarkActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-30 下午12:29
 * Description:${DESCRIPTION} 订单备注
 */
@ContentView(R.layout.activity_order_remark)
public class OrderRemarkActivity extends BaseActivity {
    @ViewInject(R.id.order_remark_rl)
    SmartRefreshLayout order_remark_rl;

    @ViewInject(R.id.order_remark_edt)
    EditText order_remark_edt;

    private String remark;

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);

        order_remark_rl.setEnableLoadMore(false);
        order_remark_rl.setEnableRefresh(false);

        Bundle bundle = getBundle();
        if (bundle != null){
            remark = bundle.getString(ORDER_REMARK);
            if (!isEmpty(remark)){
                order_remark_edt.setText(remark);
            }
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK){
//            back();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this).setLeftImageRes(R.mipmap.base_title_back).setLeftTextOrImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        }).setMiddleTitleText(R.string.add_remark).setRightText(R.string.save_remark).setRightTextOrImageListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                back();
            }
        });
    }


    private void back(){
        Bundle bundle = new Bundle();
        bundle.putString(ORDER_REMARK,getEdtText(order_remark_edt));
        getResult(RESULT_CODE_ORDER_REMARK,bundle);
    }


}
