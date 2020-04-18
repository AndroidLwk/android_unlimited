package com.wuxiantao.wxt.ui.activity;

import android.content.res.TypedArray;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.MoreIncomeBean;
import com.wuxiantao.wxt.adapter.recview.MoreIncomeRecViewAdapter;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.utils.StatusBarUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.SHIFT_ID;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MoreIncomeActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-5 下午6:57
 * Description:${DESCRIPTION} 更多收益
 */
@ContentView(R.layout.activity_more_income)
public class MoreIncomeActivity extends BaseActivity {

    @ViewInject(R.id.more_income_rv)
    RecyclerView more_income_rv;
    @ViewInject(R.id.more_income_back)
    ImageView more_income_back;
    @Override
    public void initView() {
        setStatusBar();
        StatusBarUtils.setDarkMode(this);
        setOnClikListener(more_income_back);
        initLayout();
    }

    @Override
    public void widgetClick(int viewId) {
        if (viewId == R.id.more_income_back){
            finish();
        }
    }

    private void initLayout(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0,20);
        MoreIncomeRecViewAdapter adapter = new MoreIncomeRecViewAdapter(this,initList());
        more_income_rv.setLayoutManager(manager);
        more_income_rv.addItemDecoration(decoration);
        more_income_rv.setAdapter(adapter);
        adapter.setOnItemClickListener(new MoreIncomeRecViewAdapter.OnItemClickListener() {
            //邀请好友
            @Override
            public void onInviteFriend() {
                $startActivity(ShareThemActivity.class);
            }

            //游戏分红
            @Override
            public void onDividend() {
                $startActivity(MenuActivity.class,SHIFT_ID,3);
            }

            //升级会员
            @Override
            public void onUpgradeMember() {
                $startActivity(MenuActivity.class,SHIFT_ID,1);
            }

            //购买商品
            @Override
            public void onShopping() {
                $startActivity(MenuActivity.class,SHIFT_ID,0);
            }
        });
    }


    private List<MoreIncomeBean> initList(){
        List<MoreIncomeBean> list = new ArrayList<>();
        //获取图片资源
        TypedArray ta = getResources().obtainTypedArray(R.array.more_income_icon);
        int[] icons = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            icons[i] = ta.getResourceId(i, 0);
        }
        ta.recycle();
        String[] title = getResources().getStringArray(R.array.more_income_title);
        String[] msg = getResources().getStringArray(R.array.more_income_msg);
        String[] text = getResources().getStringArray(R.array.more_income_text);

        for (int j = 0;j < title.length;j++){
            MoreIncomeBean bean = new MoreIncomeBean();
            bean.setTitle(title[j]);
            bean.setMsg(msg[j]);
            bean.setText(text[j]);
            bean.setIcon(icons[j]);
            list.add(bean);
        }
        return list;
    }



}
