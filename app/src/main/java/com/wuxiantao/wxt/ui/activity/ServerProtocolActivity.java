package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.ServerProtocolRecViewAdapter;
import com.wuxiantao.wxt.base.BaseActivity;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;
import com.wuxiantao.wxt.ui.title.TitleBuilder;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.Arrays;
import java.util.List;

import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_CONTENT_TYPE;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_PRIVACY_POLICY;
import static com.wuxiantao.wxt.config.Constant.WEB_VIEW_TYPE_USER_AGREEMENT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ServerProtocolActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-8-8 下午4:10
 * Description:${DESCRIPTION} 服务协议 1.用户协议  2.隐私政策
 */
@ContentView(R.layout.activity_server_protocol)
public class ServerProtocolActivity extends BaseActivity {
    @ViewInject(R.id.server_protocol_rv)
    RecyclerView server_protocol_rv;

    @Override
    public void initView() {
        RecViewItemDecoration decoration = new RecViewItemDecoration(this, LinearLayoutManager.VERTICAL);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(1);
        List<String> list = Arrays.asList(getResources().getStringArray(R.array.server_protocol_title));
        ServerProtocolRecViewAdapter adapter = new ServerProtocolRecViewAdapter(this,list);
        server_protocol_rv.setLayoutManager(manager);
        server_protocol_rv.addItemDecoration(decoration);
        server_protocol_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(this::transferData);
    }

    private void transferData(int p){
        Bundle bundle = new Bundle();
        switch (p){
            //用户协议
            case 0:
                bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_USER_AGREEMENT);
                break;
            //隐私政策
            case 1:
                bundle.putInt(WEB_VIEW_CONTENT_TYPE,WEB_VIEW_TYPE_PRIVACY_POLICY);
                break;
        }
        $startActivity(ProtocolActivity.class,bundle);
    }

    @Override
    public void setTitle() {
        new TitleBuilder(this)
                .setLeftImageRes(R.mipmap.base_title_back)
                .setLeftTextOrImageListener(v -> finish())
                .setMiddleTitleText(R.string.service_protocol);
    }
}
