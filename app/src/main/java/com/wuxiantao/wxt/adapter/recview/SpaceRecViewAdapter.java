package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donkingliang.labels.LabelsView;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.bean.LableSelecedInfo;
import com.wuxiantao.wxt.bean.CommodityInfoSpesBean;
import com.wuxiantao.wxt.bean.SpceLayoutBean;

import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:SpaceRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-12 下午6:37
 * Description:${DESCRIPTION}
 */
public class SpaceRecViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {

    private Context mContext;
    private List<SpceLayoutBean> list;

    public SpaceRecViewAdapter(Context context,List<SpceLayoutBean> beans){
        this.mContext = context;
        this.list = beans;
    }

    @Override
    public int getItemViewType(int position) {
        return list.get(position).getType();
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        switch (viewType){
            case SpceLayoutBean.SPACE_TITLE:
                View titleView = LayoutInflater.from(mContext).inflate(R.layout.item_space_title,viewGroup,false);
                TitleViewHolder titleHolder = new TitleViewHolder(titleView);
                x.view().inject(titleHolder,titleView);
                return titleHolder;
            case SpceLayoutBean.SPACE_LABLE:
                View lableView = LayoutInflater.from(mContext).inflate(R.layout.item_space_lableview,viewGroup,false);
                LableViewHolder lableHolder = new LableViewHolder(lableView);
                x.view().inject(lableHolder,lableView);
                return lableHolder;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder h, int position) {
          int viewType = getItemViewType(position);
          switch (viewType){
              case SpceLayoutBean.SPACE_TITLE:
                  TitleViewHolder titleHolder = (TitleViewHolder) h;
                  titleHolder.title.setText(list.get(position).getName());
                  break;
              case SpceLayoutBean.SPACE_LABLE:
                  if (lableList.size() > 0){
                      lableList.clear();
                  }
                  LableViewHolder lableHolder = (LableViewHolder) h;
                  List<CommodityInfoSpesBean.ListBean> beanList = list.get(position).getLables();
                  for (CommodityInfoSpesBean.ListBean bean : beanList){
                      lableList.add(bean.getName());
                  }
                  lableHolder.lableView.setLabels(lableList);
                  lableHolder.lableView.getSelectLabelDatas();
                  lableHolder.lableView.setOnLabelSelectChangeListener((label, data, isSelect, p) -> {
                      if (isSelect){
                          String name  = list.get(position - 1).getName();
                          if (listener != null){
//                              List<String> list = lableHolder.lableView.getSelectLabelDatas();
//                              for (String s : list){
//                                  LogUtils.loge("选择的标签:" + s);
//                              }
                              String l = label.getText().toString();
                              listener.onSelecedLable(new LableSelecedInfo(name,l));
                          }
                      }
                  });
                  break;
          }
    }



    private List<String> lableList = new ArrayList<>();

    private static class TitleViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_space_title_tv)
        TextView title;
        private TitleViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private static class LableViewHolder extends RecyclerView.ViewHolder{
        @ViewInject(R.id.item_space_labelview)
        LabelsView lableView;
        private LableViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private OnLableSelectedListener listener;
    public void setOnLableSelectedListener(OnLableSelectedListener l){
        this.listener = l;
    }
    public interface OnLableSelectedListener{
        void onSelecedLable(LableSelecedInfo info);
    }
}
