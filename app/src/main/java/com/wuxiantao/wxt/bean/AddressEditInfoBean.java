package com.wuxiantao.wxt.bean;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:AddressEditInfoBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-9-5 下午3:55
 * Description:${DESCRIPTION}
 */
public class AddressEditInfoBean implements Parcelable {

    private String name;
    private String number;
    private String province;
    private String city;
    private String area;
    private String address;
    private String id;

    //从序列化后的对象中创建原始对象
    public AddressEditInfoBean(Parcel in) {
        this.name = in.readString();
        this.number = in.readString();
        this.province = in.readString();
        this.city = in.readString();
        this.area = in.readString();
        this.address = in.readString();
        this.id = in.readString();
    }

    public AddressEditInfoBean(){}

    /**
     * public static final一个都不能少，内部对象CREATOR的名称也不能改变，必须全部大写。
     * 重写接口中的两个方法：
     * createFromParcel(Parcel in) 实现从Parcel容器中读取传递数据值,封装成Parcelable对象返回逻辑层，
     * newArray(int size) 创建一个类型为T，长度为size的数组，供外部类反序列化本类数组使用。
     */
    public static final Creator<AddressEditInfoBean> CREATOR = new Creator<AddressEditInfoBean>() {
        /**
         * 从序列化后的对象中创建原始对象
         */
        @Override
        public AddressEditInfoBean createFromParcel(Parcel in) {
            return new AddressEditInfoBean(in);
        }
        /**
         * 创建指定长度的原始对象数组
         */
        @Override
        public AddressEditInfoBean[] newArray(int size) {
            return new AddressEditInfoBean[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    //当前对象的内容描述,一般返回0即可
    @Override
    public int describeContents() {
        return 0;
    }

    //将当前对象写入序列化结构中
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.number);
        dest.writeString(this.province);
        dest.writeString(this.city);
        dest.writeString(this.area);
        dest.writeString(this.address);
        dest.writeString(this.id);
    }



}
