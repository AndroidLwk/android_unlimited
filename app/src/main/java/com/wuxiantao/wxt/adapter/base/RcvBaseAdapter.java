package com.wuxiantao.wxt.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.listener.OnBaseViewClickListener;
import com.wuxiantao.wxt.utils.CollectionUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:RcvBaseAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-30 上午9:02
 * Description:${DESCRIPTION}
 */
public abstract class RcvBaseAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected Context mContext;

    protected List<T> mDatas;
    private static final int EMPTY_VIEW = 1000;
    protected OnBaseViewClickListener listener;

    public RcvBaseAdapter(Context context, List<T> list) {
        this.mContext = context;
        this.mDatas = list;
    }

    //更新数据
    public void updataList(List<T> list) {
        this.mDatas = list;
        this.notifyDataSetChanged();
    }

    //更新数据
    public void updataList(List<T> list, int start, int count) {
        this.mDatas = list;
        this.notifyItemRangeChanged(start, count);
    }

    //添加数据
    public void addList(List<T> list) {
        if (!CollectionUtils.compareList(mDatas, list)) {
            this.mDatas.addAll(list);
            this.notifyItemRangeChanged(mDatas.size(), list.size());
        } else {
            this.updataList(list, mDatas.size(), list.size());
        }
    }

    //添加数据
    public void addList(List<T> list, int page) {
        if (page > 1) {
            addList(list);
        } else {
            updataList(list);
        }
    }

    //指定位置添加数据
    public void addList(int position, T t) {
        mDatas.add(position, t);
        this.notifyItemInserted(position);
    }

    //设置监听器
    public void setOnBaseViewClickListener(OnBaseViewClickListener l) {
        this.listener = l;
    }

    //获取数据
    public List<T> getList() {
        return mDatas;
    }


    //删除指定位置数据
    public void removeList(int position) {
        if (position < 0 || position > mDatas.size()) {
            return;
        }
        mDatas.remove(position);
        this.notifyItemRangeChanged(position, 1);
    }

    //删除全部数据
    public void removeAllList() {
        if (mDatas != null && !mDatas.isEmpty()) {
            mDatas.clear();
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return mDatas == null || mDatas.isEmpty() ? 1 : mDatas.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mDatas == null || mDatas.isEmpty()) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return BaseViewHolder.create(mContext, parent, getLayoutResId(viewType));
    }


    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        switch (viewType) {
            case EMPTY_VIEW:
                bindEmptyData(holder, position);
                break;
            default:
                showDriverLine(holder,position);
                convert(holder, mDatas != null && mDatas.size() > 0 ? mDatas.get(position) : null, position);
                break;
        }
    }

    protected abstract void convert(BaseViewHolder holder, T t, int position);

    protected int getLayoutResId(int viewType) {
        switch (viewType) {
            case EMPTY_VIEW:
                return getEmptyLayoutId();
            default:
                return getLayoutId(viewType);
        }
    }

    protected void bindEmptyData(BaseViewHolder holder, int position) {

    }

    private void showDriverLine(BaseViewHolder holder,int position) {
        boolean isLast = mDatas.size() - 1 == position;
        holder.setVisibility(getDriverLineId(), isLast ? View.GONE : View.VISIBLE);
    }

    protected int getDriverLineId(){
        return 0;
    }

    protected abstract int getLayoutId(int viewType);

    protected int getEmptyLayoutId() {
        return R.layout.item_base_empty_layout;
    }

    protected boolean isEmpty(String s) {
        return s == null || TextUtils.isEmpty(s);
    }
}
