package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.bean.AddressEditInfoBean;
import com.wuxiantao.wxt.bean.AddressListBean;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-3 上午11:04
 * Description:${DESCRIPTION}
 */
public class AddressRecViewAdapter extends RcvBaseAdapter<AddressListBean.ListBean> {


    public AddressRecViewAdapter(Context context, List<AddressListBean.ListBean> list) {
        super(context, list);
    }

    @Override
    protected void convert(BaseViewHolder holder, AddressListBean.ListBean bean, int position) {
        String name = bean.getUsername();
        String number = bean.getPhone();
        String id = String.valueOf(bean.getId());
        String province = bean.getProvince();
        String city = bean.getCity();
        String area = bean.getArea();
        String address = bean.getAddress();
        int is_default = bean.getIs_default();
        String info = getAddressInfo(province,city,area,address);
        holder.setText(R.id.item_address_management_name,name);
        holder.setText(R.id.item_address_management_number, NumberFormatUtils.phoneNumberSub(number));
        holder.setText(R.id.item_address_management_address,info);
        if (is_default == 1){
            holder.setViewBackGroundResources(R.id.item_address_management_default_icon,R.mipmap.address_selected_icon);
            holder.setText(R.id.item_address_management_default_tv,mContext.getString(R.string.default_address));
            holder.setTextColor(R.id.item_address_management_default_tv,"#FFBA20");
        }else {
            holder.setViewBackGroundResources(R.id.item_address_management_default_icon,R.mipmap.address_select_icon);
            holder.setText(R.id.item_address_management_default_tv,mContext.getString(R.string.set_default_address));
            holder.setTextColor(R.id.item_address_management_default_tv,"#333333");
        }

        holder.setViewOnClickListener(R.id.item_address_management_edit, v -> {
            if (listener != null){
                AddressEditInfoBean infoBean = new AddressEditInfoBean();
                infoBean.setName(name);
                infoBean.setNumber(number);
                infoBean.setId(id);
                infoBean.setProvince(province);
                infoBean.setCity(city);
                infoBean.setArea(area);
                infoBean.setAddress(address);
                listener.onEditAddress(infoBean);
            }
        });
        holder.setViewOnClickListener(R.id.item_address_management_default, v -> {
            if (listener != null){
                listener.onDefaultAddress(id);
            }
        });
        holder.setViewOnClickListener(R.id.item_address_management_delete, v -> {
            if (listener != null){
                listener.onDeleteAddress(id);
            }
        });
    }

    private StringBuilder sb = new StringBuilder();

    private String getAddressInfo(String ...strings){
        sb.setLength(0);
        if (strings.length <= 0){
            return "";
        }
        for (String string : strings) {
            sb.append(string);
        }
        return sb.toString();
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_address_management;
    }

    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public interface OnItemClickListener{
        void onEditAddress(AddressEditInfoBean infoBean);
        void onDefaultAddress(String address_id);
        void onDeleteAddress(String address_id);
    }
}
