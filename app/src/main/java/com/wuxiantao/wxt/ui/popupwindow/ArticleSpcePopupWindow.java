package com.wuxiantao.wxt.ui.popupwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.donkingliang.labels.LabelsView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.LableSelecedInfo;
import com.wuxiantao.wxt.adapter.recview.SpaceRecViewAdapter;
import com.wuxiantao.wxt.bean.CommodityInfoBean;
import com.wuxiantao.wxt.bean.CommodityInfoSpesBean;
import com.wuxiantao.wxt.bean.SpceLayoutBean;
import com.wuxiantao.wxt.net.gson.GsonManager;
import com.wuxiantao.wxt.ui.custom.decoration.SpaceItemDecoration;
import com.wuxiantao.wxt.ui.popupwindow.base.BaseBuild;
import com.wuxiantao.wxt.ui.popupwindow.base.BasePopupWindow;
import com.wuxiantao.wxt.utils.BigDecimalUtils;

import java.util.ArrayList;
import java.util.List;

import static com.wuxiantao.wxt.bean.SpceLayoutBean.SPACE_LABLE;
import static com.wuxiantao.wxt.bean.SpceLayoutBean.SPACE_TITLE;
import static com.wuxiantao.wxt.config.Constant.RESOURCES;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ActivationFriendPopupWindow
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-19 上午10:44
 * Description:${DESCRIPTION} 商品规格
 */
public class ArticleSpcePopupWindow extends BasePopupWindow {


    public ArticleSpcePopupWindow(Build build) {
        super(build);
    }


    public static class Build extends BaseBuild {

        private OnPopupClickListener listener;
        private String count = null;
        private List<CommodityInfoSpesBean> spesBean;
        private List<LableSelecedInfo> defaultLables = new ArrayList<>();
        private List<LableSelecedInfo> selecedLables = new ArrayList<>();

        public Build(Context context) {
            super(context, R.layout.popupwindow_article_spce);
            setOnButtonListener(R.id.popup_article_spce_close,R.id.popup_article_spce_buy);
        }



        public Build setPopupWindowWidth(int width) {
            super.setPopupWindowWidth(width);
            return this;
        }

        public <T>Build setImageResource(T url) {
            super.setRoundImageResource(R.id.popup_article_spce_img,5, url);
            return this;
        }

        public Build setOnPopupClickListener(OnPopupClickListener l){
            this.listener = l;
            return this;
        }

        public Build setLablesViewList(CommodityInfoBean infoBean) {
            if (infoBean == null){
                return this;
            }
            //标题
             setText(infoBean.getInfo().getGoods_title(),R.id.popup_article_spce_title);
             //价格
             String price = infoBean.getInfo().getPrice();
             setText(BigDecimalUtils.round(price,2),R.id.popup_article_spce_money);
             //库存数量
              int total_number = infoBean.getInfo().getTotal_number();
             setText(getString(R.string.stock,total_number),R.id.popup_article_spce_stock);
             //解析json
             String specs = infoBean.getInfo().getSpecs();
             if (!isEmpty(specs)){
                spesBean = GsonManager.newInstance().jsonToArrayList(specs,CommodityInfoSpesBean.class);
             }
            //规格
            if (spesBean != null && spesBean.size() > 0){
                List<SpceLayoutBean> list = new ArrayList<>();
                for (int i = 0;i < spesBean.size();i++){
                    String title = spesBean.get(i).getName();
                    //赋默认值
                    defaultLables.add(new LableSelecedInfo(title,spesBean.get(i).getList().get(0).getName()));
                    List<CommodityInfoSpesBean.ListBean> listBeans = spesBean.get(i).getList();
                    list.add(new SpceLayoutBean(SPACE_TITLE,title));
                    list.add(new SpceLayoutBean(SPACE_LABLE,listBeans));
                }
                SpaceRecViewAdapter adapter = new SpaceRecViewAdapter(mContext,list);
                LinearLayoutManager manager = new LinearLayoutManager(mContext);
                manager.setOrientation(1);
                SpaceItemDecoration decoration = new SpaceItemDecoration(20,0);

                RecyclerView spaceRv = findViewById(R.id.popup_article_spce_rv);
                spaceRv.setLayoutManager(manager);
                spaceRv.addItemDecoration(decoration);
                spaceRv.setAdapter(adapter);
                adapter.setOnLableSelectedListener(info -> {
                    //判断元素是否重复
                    if (!selecedLables.contains(info)){
                         selecedLables.add(info);
                    }else {
                        //删除重复元素
                        selecedLables.remove(info);
                        selecedLables.add(info);
                    }
                });
                //数量
                LabelsView countLableView = findViewById(R.id.popup_article_count_lv);
                List<String> countList = new ArrayList<>();
                countList.add(RESOURCES.getString(R.string.commodity_quantity));
                count = countList.get(0);
                countLableView.setLabels(countList);
                countLableView.setOnLabelSelectChangeListener((label, data, isSelect, position)
                        -> count = label.getText().toString().trim());
            }
            return this;
        }


        public Build setPopupWindowAnimStyle(int animationStyle) {
            super.setPopupWindowAnimStyle(animationStyle);
            return this;
        }


        @Override
        public BasePopupWindow builder() {
            return new ArticleSpcePopupWindow(this);
        }

        @Override
        public void onClick(int viewId) {
            switch (viewId){
                case R.id.popup_article_spce_close:
                    if (listener != null){
                        listener.onClose(selecedLables == null || selecedLables.size() <= 0 ? defaultLables : selecedLables,count);
                    }
                    break;
                case R.id.popup_article_spce_buy:
                    if (listener != null){
                        listener.onImmediatelyBuy(selecedLables == null || selecedLables.size() <= 0 ? defaultLables : selecedLables,count);
                    }
                    break;
            }
        }

        public interface OnPopupClickListener{
            void onImmediatelyBuy(List<LableSelecedInfo> spaces,String count);
            void onClose(List<LableSelecedInfo> spaces,String count);
        }
    }

}
