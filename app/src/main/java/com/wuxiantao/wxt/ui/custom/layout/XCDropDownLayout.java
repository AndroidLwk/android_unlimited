package com.wuxiantao.wxt.ui.custom.layout;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.recview.DropdownRecViewAdapter;
import com.wuxiantao.wxt.bean.ComplaintSortBean;
import com.wuxiantao.wxt.ui.custom.decoration.RecViewItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:XCDropDownLayout
 * Author:android
 * Mail:2898682029@qq.com
 * Date:20-1-3 下午10:45
 * Description:${DESCRIPTION} 自定义下拉列边框
 */
public class XCDropDownLayout extends LinearLayout implements View.OnClickListener{

    private TextView title;
    private ImageView img;
    private List<ComplaintSortBean> datas = new ArrayList<>();
    private PopupWindow mPopupWindow;
    private ObjectAnimator mObjectAnimator;
    private AnimatorSet mAnimatorSet;
    private RelativeLayout layout;

    public XCDropDownLayout(Context context) {
        this(context,null);
    }

    public XCDropDownLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public XCDropDownLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context){
        View view = LayoutInflater.from(context).inflate(R.layout.xc_dropdown_layout,this,true);
        title = view.findViewById(R.id.xc_dropdown_layout_title);
        img = view.findViewById(R.id.xc_dropdown_layout_img);
        layout = view.findViewById(R.id.xc_dropdown_layout);
        this.setOnClickListener(this);
    }

    public void setViewBackground(@DrawableRes int id){
        if (layout != null && id != 0){
            layout.setBackgroundResource(id);
        }
    }

    @Override
    public void onClick(View v) {
        if (mPopupWindow == null){
            showPopWindow();
        }else {
            closePopWindow();
        }
    }

    private void dropUpAnim(){
        mObjectAnimator = ObjectAnimator.ofFloat(img,"rotation",0f,180f);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(mObjectAnimator);
        mAnimatorSet.setDuration(500);
        mAnimatorSet.start();
    }

    private void dropDownAnim(){
        mObjectAnimator = ObjectAnimator.ofFloat(img,"rotation",180f,0f);
        mAnimatorSet = new AnimatorSet();
        mAnimatorSet.play(mObjectAnimator);
        mAnimatorSet.setDuration(500);
        mAnimatorSet.start();
    }


    @SuppressLint("InflateParams")
    private void showPopWindow(){
        dropDownAnim();
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popup_xc_dropdown,null);
        RecyclerView rv = view.findViewById(R.id.popupwindow_xc_dropdown_rv);
        initLayout(rv);
        initPopupWindow(view);
    }

    private void initLayout(RecyclerView rv){
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.VERTICAL);
        RecViewItemDecoration decoration = new RecViewItemDecoration(0, Color.parseColor("#747474"));
        DropdownRecViewAdapter adapter = new DropdownRecViewAdapter(getContext(),datas);
        rv.setLayoutManager(manager);
        rv.addItemDecoration(decoration);
        rv.setAdapter(adapter);
        adapter.setOnDropdowSelectedListener((data, id) -> {
            if (listener != null){
                title.setText(data);
                listener.onSelectedData(id);
                closePopWindow();
            }
        });
    }

    private void initPopupWindow(View view){
        int width = LayoutParams.WRAP_CONTENT;
        int height = LayoutParams.WRAP_CONTENT;
        mPopupWindow = new PopupWindow(view,width,height);
        mPopupWindow.setBackgroundDrawable(getResources().getDrawable(R.color.alibc_transparent));
        mPopupWindow.setOutsideTouchable(false);
        mPopupWindow.showAsDropDown(this);
        cancelAnim();
    }


    public void closePopWindow(){
        dropUpAnim();
        if (mPopupWindow != null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        cancelAnim();
    }

    private void cancelAnim(){
        img.clearAnimation();
        if (mObjectAnimator != null){
            mObjectAnimator.cancel();
            mObjectAnimator = null;
        }
        if (mAnimatorSet != null){
            mAnimatorSet.cancel();
            mAnimatorSet = null;
        }
    }

    public void setItemsData(List<ComplaintSortBean> list){
        this.datas = list;
        if (datas != null && !datas.isEmpty()){
            title.setText(datas.get(0).getName());
        }
    }



    private OnDropdowSelectedListener listener;

    public void setOnDropdowSelectedListener(OnDropdowSelectedListener listener){
        this.listener = listener;
    }
    public interface OnDropdowSelectedListener{
        void onSelectedData(int id);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (KeyEvent.KEYCODE_BACK == keyCode){
            closePopWindow();
        }
        return super.onKeyDown(keyCode, event);
    }
}
