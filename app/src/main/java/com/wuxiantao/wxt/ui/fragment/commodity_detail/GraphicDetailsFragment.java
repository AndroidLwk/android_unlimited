package com.wuxiantao.wxt.ui.fragment.commodity_detail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.GraphicDetailsRecViewAdapter;
import com.wuxiantao.wxt.base.BaseFragment;
import com.wuxiantao.wxt.event.MessageEvent;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.custom.scroller.TopSmoothScroller;

import org.greenrobot.eventbus.EventBus;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mtopsdk.common.util.StringUtils;

import static com.wuxiantao.wxt.config.Constant.COMMODITY_DETAIL_TOPPING;
import static com.wuxiantao.wxt.config.Constant.GOOD_CONTENT;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:GraphicDetailsFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-24 下午6:39
 * Description:${DESCRIPTION} 商品详情->图文详情
 */
@SuppressLint("ValidFragment")
@ContentView(R.layout.fragment_graphic_details)
public class GraphicDetailsFragment extends BaseFragment {
    @ViewInject(R.id.fragment_graphic_details_rv)
    RecyclerView fragment_graphic_details_rv;
    @ViewInject(R.id.fragment_graphic_details_topping)
    ImageView fragment_graphic_details_topping;

    private TopSmoothScroller mScroller;
    private LinearLayoutManager manager;

    @Override
    public void initView() {
        mScroller = new TopSmoothScroller(getContext());
        Bundle bundle = getArguments();
        if (bundle != null) {
            String good_content = bundle.getString(GOOD_CONTENT);
            if (!isEmpty(good_content)) {
                ArrayList<String> srcList = extractImg(good_content);
                initLayout(srcList);
            }else {
                initLayout(null);
            }
        }
        setOnClikListener(fragment_graphic_details_topping);
    }


    @Override
    protected void widgetClick(int id) {
        if (id == R.id.fragment_graphic_details_topping){
            if (manager != null && mScroller != null){
                EventBus.getDefault().post(new MessageEvent(COMMODITY_DETAIL_TOPPING));
                mScroller.setTargetPosition(0);
                manager.startSmoothScroll(mScroller);
            }
        }
    }

    private void initLayout(ArrayList<String> srcList){
        manager = new LinearLayoutManager(getContext());
        manager.setOrientation(1);
        SpaceItemDecoration decoration = new SpaceItemDecoration(0,20);
        GraphicDetailsRecViewAdapter adapter = new GraphicDetailsRecViewAdapter(getContext(),srcList);
        fragment_graphic_details_rv.setLayoutManager(manager);
        fragment_graphic_details_rv.addItemDecoration(decoration);
        fragment_graphic_details_rv.setAdapter(adapter);
        adapter.setOnBaseViewClickListener(position -> previewImg(srcList,position));
    }



    /**
     * 从HTML中提取图片链接
     * @param content 副文本
     */
    public static ArrayList<String> extractImg(String content) {
        // 用来存储获取到的图片地址
        ArrayList<String> srcList = new ArrayList<>();
        if (StringUtils.isBlank(content)) {
            return srcList;
        }
        // 匹配字符串中的img标签
        Pattern p = Pattern.compile("<(img|IMG)(.*?)(>|></img>|/>)");
        Matcher matcher = p.matcher(content);
        boolean hasPic = matcher.find();
        // 判断是否含有图片
        if (hasPic) {
            // 如果含有图片，那么持续进行查找，直到匹配不到
            while (hasPic) {
                // 获取第二个分组的内容，也就是 (.*?)匹配到的
                String group = matcher.group(2);
                // 匹配图片的地址
                Pattern srcText = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");
                Matcher matcher2 = srcText.matcher(group);
                if (matcher2.find()) {
                    // 把获取到的图片地址添加到列表中
                    srcList.add(matcher2.group(3));
                }
                // 判断是否还有img标签
                hasPic = matcher.find();
            }

        }
        return srcList;
    }



}
