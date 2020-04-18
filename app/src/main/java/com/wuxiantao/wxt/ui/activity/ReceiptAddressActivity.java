package com.wuxiantao.wxt.ui.activity;

import android.os.Bundle;
import android.text.InputFilter;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lljjcoder.citypickerview.widget.CityPicker;
import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.bean.AddressEditInfoBean;
import com.wuxiantao.wxt.mvp.contract.ReceiptContract;
import com.wuxiantao.wxt.mvp.presenter.ReceiptPresenter;
import com.wuxiantao.wxt.mvp.view.activity.MvpActivity;
import com.wuxiantao.wxt.ui.custom.button.StateButton;
import com.wuxiantao.wxt.ui.custom.edittext.filter.MaxTextLengthFilter;
import com.wuxiantao.wxt.ui.dialog.LoadingDialog;
import com.wuxiantao.wxt.utils.StatusBarUtil;
import com.wuxiantao.wxt.utils.ToastUtils;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;

import java.util.HashMap;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ADDRESS_ADD_TYPE;
import static com.wuxiantao.wxt.config.Constant.ADDRESS_EDIT_TYPE;
import static com.wuxiantao.wxt.config.Constant.ADDRESS_TYPE;
import static com.wuxiantao.wxt.config.Constant.EDIT_ADDRESS_INFO;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_ADD_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.RESULT_CODE_EDIT_ADDRESS;
import static com.wuxiantao.wxt.config.Constant.TOKEN;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:ReceiptAddressActivity
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-17 下午4:35
 * Description:${DESCRIPTION} 编辑收获地址
 */
@ContentView(R.layout.activity_receipt_address)
public class ReceiptAddressActivity extends MvpActivity<ReceiptPresenter, ReceiptContract.IReceiptView> implements ReceiptContract.IReceiptView {
    @ViewInject(R.id.receipt_address_back)
    RelativeLayout receipt_address_back;
    @ViewInject(R.id.receipt_address_title)
    TextView receipt_address_title;
    @ViewInject(R.id.edit_address_city_select)
    TextView edit_address_city_select;
    @ViewInject(R.id.edit_address_person)
    EditText edit_address_person;
    @ViewInject(R.id.edit_address_number)
    EditText edit_address_number;
    @ViewInject(R.id.edit_address_detail)
    EditText edit_address_detail;
    @ViewInject(R.id.edit_address_save)
    StateButton edit_address_save;

    private int type = 0;
    private LoadingDialog loadingDialog;
    private Map<String,Object> map = new HashMap<>();
    private String name;
    private String number;
    private String address_id;
    private String province;
    private String city;
    private String area;
    private String address;

    @Override
    protected ReceiptPresenter CreatePresenter() {
        return new ReceiptPresenter();
    }

    @Override
    public void initView() {
        StatusBarUtil.setStatusBarColor(this,getResources().getColor(R.color.white));
        StatusBarUtil.setStatusBarDarkTheme(this,true);
        edit_address_person.setFilters(new InputFilter[]{new MaxTextLengthFilter(5)});
        edit_address_number.setFilters(new InputFilter[]{new MaxTextLengthFilter(11)});
        edit_address_detail.setFilters(new InputFilter[]{new MaxTextLengthFilter(60)});
        Bundle bundle = getBundle();
        if (bundle != null){
            type = bundle.getInt(ADDRESS_TYPE);
            switch (type){
                case ADDRESS_ADD_TYPE:
                    receipt_address_title.setText(R.string.add_address);
                    break;
                case ADDRESS_EDIT_TYPE:
                    receipt_address_title.setText(R.string.edit_address);
                    AddressEditInfoBean infoBean = bundle.getParcelable(EDIT_ADDRESS_INFO);
                    if (infoBean != null){
                         name = infoBean.getName();
                         number = infoBean.getNumber();
                         address_id = infoBean.getId();
                         province = infoBean.getProvince();
                         city = infoBean.getCity();
                         area = infoBean.getArea();
                         address = infoBean.getAddress();
                        if (!isEmpty(name,number,address_id,address)){
                            edit_address_person.setText(name);
                            edit_address_person.setSelection(name.length());
                            edit_address_number.setText(number);
                            edit_address_city_select.setText(getString(R.string.province_city_regex,province,city,area));
                            edit_address_detail.setText(address);
                        }
                    }
                    break;
            }
        }
        setOnClikListener(edit_address_city_select,edit_address_save,receipt_address_back);
    }

    @Override
    public void widgetClick(int viewId) {
        switch (viewId){
            case R.id.receipt_address_back:
                finish();
                break;
            case R.id.edit_address_city_select:
                chooseArea();
                break;
            case R.id.edit_address_save:
                name = getEdtText(edit_address_person);
                number = getEdtText(edit_address_number);
                address = getEdtText(edit_address_detail);
                if (isEmpty(name,number,address)){
                    ToastUtils.warning(getString(R.string.plase_input_complete_info));
                    return;
                }
                map.put(TOKEN,getAppToken());
                map.put("province",province);
                map.put("city",city);
                map.put("area",area);
                map.put("username",name);
                map.put("phone",number);
                map.put("address",address);
                switch (type){
                    //添加地址
                    case ADDRESS_ADD_TYPE:
                        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.add_address_loading).build();
                        mPresenter.addAddress(map);
                        break;
                    //编辑地址
                    case ADDRESS_EDIT_TYPE:
                        map.put("address_id",address_id);
                        loadingDialog = new LoadingDialog.Build(this).setLoadingText(R.string.update_address_loading).build();
                        mPresenter.updateAddress(map);
                        break;
                }
                break;
        }
    }

    //Texview的点击事件
    private void chooseArea(){
        //判断输入法的隐藏状态
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm.isActive()){
            imm.hideSoftInputFromWindow(edit_address_city_select.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }
        selectAddress();
    }

    //选择地址
    private void selectAddress(){
        //https://www.cnblogs.com/jingAndroid/p/6707972.html
        CityPicker mCityPicker = new CityPicker.Builder(this)
                   .textSize(20) //滚轮文字的大小，int 类型，默认为18
                   .title(getString(R.string.address_select)) //选择器标题，默认为“选择地区”
                   .titleBackgroundColor("#ffffff") //标题栏背景，默认为灰色，#C7C7C7
                   .textColor(R.color.black) //滚轮文字的颜色 ，int 类型，默认为0xFF585858
                   .confirTextColor("#000000") //确认按钮字体颜色，默认为系统的colorPrimary颜色值
                   .cancelTextColor("#696969") //取消按钮字体颜色，默认为系统的colorPrimary颜色值
                   .province(getResources().getString(R.string.province)) //默认的显示省份，显示选择器后直接定位的item位置
                   .city(getResources().getString(R.string.current_city)) //默认的显示市，显示选择器后直接定位的item位置
                   .district(getResources().getString(R.string.district)) //默认的显示区，显示选择器后直接定位的item位置
                   .provinceCyclic(true) //省份的滚轮是否循环滚动
                   .cityCyclic(false) //市的滚轮是否循环滚动
                   .districtCyclic(false) //区的滚轮是否循环滚动
                   .visibleItemsCount(7) //滚轮显示的item个数，int 类型，默认为5个
                   .itemPadding(10) //滚轮item间距，默认为5dp
                   .onlyShowProvinceAndCity(false) //是否只显示省份和市的两级联动，去掉区或者县
                   .build();
        mCityPicker.show();
        //监听方法，获取选择结果
        mCityPicker.setOnCityItemClickListener(citySelected -> {
            //省份
             province = citySelected[0];
            //城市
            city = citySelected[1];
            //区县（如果设定了两级联动，那么该项返回空）
            area = citySelected[2];
            //邮编
            String code = citySelected[3];

            //为TextView赋值
            edit_address_city_select.setText(getString(R.string.province_city_regex,province,city,area));
        });
    }

    //地址修改成功
    @Override
    public void updateAddressSuccess(String msg) {
        showOnlyConfirmDialog(msg, (dialog, which) ->
                getResult(RESULT_CODE_EDIT_ADDRESS));
    }

    @Override
    public void updateAddressFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    //地址添加成功
    @Override
    public void addAddressSuccess(String id) {
        showOnlyConfirmDialog(getString(R.string.add_address_success), (dialog, which) ->
                getResult(RESULT_CODE_ADD_ADDRESS));
    }



    @Override
    public void addAddressFailure(String failure) {
        showOnlyConfirmDialog(failure);
    }

    @Override
    public void showLoading() {
        loadingDialog.showLoadingDialog();
    }

    @Override
    public void dismissLoading() {
        loadingDialog.dismissLoadingDialog();
    }
}
