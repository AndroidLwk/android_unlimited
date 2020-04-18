package com.wuxiantao.wxt.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.count_down.CountDownCallBack;
import com.wuxiantao.wxt.count_down.CountDownManager;
import com.wuxiantao.wxt.ui.activity.PreviewImgActivity;
import com.wuxiantao.wxt.utils.DateUtils;

import org.xutils.x;

import java.util.ArrayList;

import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_LIST;
import static com.wuxiantao.wxt.config.Constant.PREVIEW_IMG_POSITION;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:BaseFragment
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-5-27 下午7:29
 * Description:${DESCRIPTION}
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected Activity mActivity;
    protected Context mContext;
    private   long lastClick = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        mContext = getContext();
        return x.view().inject(this,inflater,container);
    }

    @Override
    public void onClick(View v) {
        if (fastClick()){
            widgetClick(v.getId());
        }
    }




    private boolean fastClick() {
        if (System.currentTimeMillis() - lastClick <= 500){
            return false;
        }
        lastClick = System.currentTimeMillis();
        return true;
    }

    protected  void setOnClikListener(View ...components) {
        if (components.length <= 0){
            return;
        }
        for (View v : components){
            v.setOnClickListener(this);
        }

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setTitle();
        initView();
    }

    private Bundle  addIntegerBundle(String key,Object value){
        Bundle  bundle = new Bundle();
        if (value instanceof Integer){
            bundle.putInt(key,(Integer) value);
        }
        else if (value instanceof String){
            bundle.putString(key,(String) value);
        }
        else if (value instanceof Double){
            bundle.putDouble(key,(Double) value);
        }
        return bundle;
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla) {
        Intent intent = new Intent(getActivity(),cla);
        getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     */
    protected void $startActivity(Class<?> cla,Bundle bundle) {
        if (bundle != null){
            Intent intent = new Intent(getActivity(),cla);
            intent.putExtras(bundle);
            getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            startActivity(intent);
        }else {
            $startActivity(cla);
        }
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value) {
        Intent intent = new Intent(getActivity(),cla);
        intent.putExtras(addIntegerBundle(key,value));
//        BaseApplication.getInstance().addActivity(getActivity());
        getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param key
     * @param value
     */
    protected void $startActivity(Class<?> cla,String key,Object value,int enterAnim, int exitAnim) {
        Intent intent = new Intent(getActivity(),cla);
        intent.putExtras(addIntegerBundle(key,value));
//        BaseApplication.getInstance().addActivity(getActivity());
        getActivity().overridePendingTransition(enterAnim,exitAnim);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param enterAnim
     * @param exitAnim
     */
    protected void $startActivity(Class<?> cla,int enterAnim, int exitAnim) {
        Intent intent = new Intent(getActivity(),cla);
//        BaseApplication.getInstance().addActivity(getActivity());
        getActivity().overridePendingTransition(enterAnim,exitAnim);
        startActivity(intent);
    }

    /**
     * 界面跳转
     * @param cla
     * @param str
     */
    protected void $startActivity(Class<?> cla,String ...str) {
        if (str.length <= 0){
            $startActivity(cla);
        }else {
            Bundle bundle = new Bundle();
            String key = "";
            String value = "";
            for (int i = 0;i < str.length;i++){
                if (i % 2 == 0){
                    key = str[i];
                }else {
                    value = str[i];
                }
                bundle.putString(key,value);
            }
            Intent intent = new Intent(getActivity(),cla);
            intent.putExtras(bundle);
            getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            $startActivity(cla);
        }
    }

    /**
     *  界面跳转
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla,int requestCode) {
        Intent intent = new Intent(getActivity(),cla);
//        BaseApplication.getInstance().addActivity(getActivity());
        getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
        startActivityForResult(intent,requestCode);
    }

    /**
     *  界面跳转
     * @param cla
     */
    protected void $startActivityForResult(Class<?> cla,Bundle bundle,int requestCode) {
        if (bundle == null){
            $startActivityForResult(cla,requestCode);
        }else {
            Intent intent = new Intent(getActivity(),cla);
//            BaseApplication.getInstance().addActivity(getActivity());
            intent.putExtras(bundle);
            getActivity().overridePendingTransition(R.anim.translate_into,R.anim.translate_into);
            startActivityForResult(intent,requestCode);
        }
    }

    //获取 APP 详情页面intent
    public  Intent getAppDetailSettingIntent() {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package",mActivity.getPackageName(), null));
        }
        else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", mActivity.getPackageName());
        }
        return localIntent;
    }

    protected void previewImg(ArrayList<String> imgList, int position) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(PREVIEW_IMG_LIST, imgList);
        bundle.putInt(PREVIEW_IMG_POSITION, position);
        $startActivity(PreviewImgActivity.class, bundle);
    }

    protected boolean isEmpty(String s){
        return s == null || TextUtils.isEmpty(s);
    }

    public void setTitle(){}

    public void initView(){}

    protected  void widgetClick(int id){}
}
