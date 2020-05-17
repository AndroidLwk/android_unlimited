package com.wuxiantao.wxt.adapter.recview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;

import com.wuxiantao.wxt.R;
import com.wuxiantao.wxt.adapter.base.BaseViewHolder;
import com.wuxiantao.wxt.adapter.base.RcvBaseAdapter;
import com.wuxiantao.wxt.utils.NumberFormatUtils;

import java.util.List;
import java.util.Map;

import static com.wuxiantao.wxt.config.Constant.ABOUT_SUPER;
import static com.wuxiantao.wxt.config.Constant.ALI_CODE;
import static com.wuxiantao.wxt.config.Constant.ALI_NAME;
import static com.wuxiantao.wxt.config.Constant.APP_USER_ID;
import static com.wuxiantao.wxt.config.Constant.IS_SETTING_PW;
import static com.wuxiantao.wxt.config.Constant.IS_TAO_BAO_AUTH;
import static com.wuxiantao.wxt.config.Constant.NICK_NAME;
import static com.wuxiantao.wxt.config.Constant.NUMBER;
import static com.wuxiantao.wxt.config.Constant.USER_HEAD_IMG;
import static com.wuxiantao.wxt.config.Constant.WECHAT_NO;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:MyInformationRecViewAdapter
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-3 上午9:36
 * Description:${DESCRIPTION} 个人信息列表适配器
 */
public class MyInformationRecViewAdapter extends RcvBaseAdapter<String> {

    private Map<String, Object> map;

    public MyInformationRecViewAdapter(Context context, List<String> list, Map<String, Object> map) {
        super(context, list);
        this.map = map;
    }

    public <T> void updataMap(String key, T value) {
        map.put(key, value);
        this.notifyDataSetChanged();
    }


    @Override
    public void convert(BaseViewHolder holder, String s, int position) {
        holder.setVisibility(R.id.item_my_information_head_img, position == 0 ? View.VISIBLE : View.INVISIBLE);
        holder.setText(R.id.item_my_information_title, s);
        String headImg = (String) map.get(USER_HEAD_IMG);
        String userId = (String) map.get(APP_USER_ID);
        String nickName = (String) map.get(NICK_NAME);
        String number = (String) map.get(NUMBER);
        String account = (String) map.get(ALI_CODE);
        String wechat_id = (String) map.get(WECHAT_NO);
        String name = (String) map.get(ALI_NAME);
        String verName = (String) map.get(ABOUT_SUPER);
        boolean isSettingPassWord = map.get(IS_SETTING_PW) == null || (boolean) map.get(IS_SETTING_PW);
        boolean isVer = map.get(IS_TAO_BAO_AUTH) != null && (boolean) map.get(IS_TAO_BAO_AUTH);
        boolean isBindingNumber = !isEmpty(number);

        holder.setTextColor(R.id.item_my_information_content, (isEmpty(number) && position == 3) ||
                (isEmpty(account) && position == 4) || (isEmpty(wechat_id) && position == 6) ? Color.RED : Color.GRAY);

        //头像
        if (position == 0) {
            holder.setCircleImageResource(R.id.item_my_information_head_img,
                    !isEmpty(headImg) ? headImg : R.drawable.ic_person_outline_black_24dp);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onChangeHeadImg();
                }
            });
            holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
            holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
        }
        //id
        if (position == 1 && !isEmpty(userId)) {
            holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
            holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
            holder.setText(R.id.item_my_information_content, userId);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onShowUserId(userId);
                }
            });
        }
        //昵称
        if (position == 2) {
            String tiop = mContext.getResources().getString(R.string.please_set_nick_name);
            holder.setText(R.id.item_my_information_content, isEmpty(nickName) ? tiop : nickName);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onChangeNickName(nickName);
                }
            });
            holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
            holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
        }
        //手机号
        if (position == 4) {
            holder.setVisibility(R.id.item_my_information_prize_icon, isBindingNumber ? View.GONE : View.VISIBLE);
            holder.setVisibility(R.id.item_my_information_prize_money, isBindingNumber ? View.GONE : View.VISIBLE);
            if (isBindingNumber) {
                String sub = NumberFormatUtils.phoneNumberSub(number);
                holder.setText(R.id.item_my_information_content, sub);
                holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                    if (l != null) {
                        l.onChangeNumber(number);
                    }
                });
            } else {
                holder.setText(R.id.item_my_information_prize_money, String.valueOf(20));
                holder.setText(R.id.item_my_information_content, R.string.go_binding);
                holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                    if (l != null) {
                        l.onBindNumber();
                    }
                });
            }
        }

        //支付宝
        if (position == 5) {
            setAlipayItem(holder, account, name);
        }

        //微信号
        if (position == 6) {
            setWeChatItem(holder, wechat_id);
        }

        //收货地址
//        if (position == 6){
//            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
//                if (l != null){
//                    l.onAddressManagement();
//                }
//            });
//            holder.setVisibility(R.id.item_my_information_prize_icon,View.GONE);
//            holder.setVisibility(R.id.item_my_information_prize_money,View.GONE);
//        }

        //已绑定手机号
        if (isBindingNumber) {
            setViewVisibility(holder, position, 7);
            //登陆密码
//            if (position == 7){
//                String set = mContext.getResources().getString(R.string.please_set_pw);
//                String seted = mContext.getResources().getString(R.string.seted);
//                holder.setText(R.id.item_my_information_content,isSettingPassWord ? set : seted);
//                holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
//                    if (l != null){
//                        l.onSettingPassWord(isSettingPassWord,number);
//                    }
//                });
//                holder.setVisibility(R.id.item_my_information_prize_icon,View.GONE);
//                holder.setVisibility(R.id.item_my_information_prize_money,View.GONE);
//            }
            if (position == 3) {
                holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
                holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
            }
            //淘宝授权
            if (position == 7) {
                setTaoBaoVerItem(holder, isVer);
            }
            //关于无限淘
//            if (position == 9){
//                setAboutWXTItem(holder,verName);
//            }
        } else {
            setViewVisibility(holder, position, 7);
            //淘宝授权
            if (position == 7) {
                setTaoBaoVerItem(holder, isVer);
            }
            //关于无限淘
//            if (position == 8){
//                setAboutWXTItem(holder,verName);
//            }
        }
    }

    private void setViewVisibility(BaseViewHolder holder, int position, int index) {
        holder.setVisibility(R.id.item_my_information_content, position == 0 || position == index ? View.INVISIBLE : View.VISIBLE);
        holder.setVisibility(R.id.item_my_information_sb, position == index ? View.VISIBLE : View.INVISIBLE);
        holder.setVisibility(R.id.item_my_information_back_img, position == 1 || position == index ? View.INVISIBLE : View.VISIBLE);
        if (position == 3) {
            holder.setVisibility(R.id.item_my_information_back_img, View.INVISIBLE);
        }
    }

    private void setAlipayItem(BaseViewHolder holder, String alicode, String aliname) {
        if (!isEmpty(alicode) && !isEmpty(aliname)) {
            holder.setText(R.id.item_my_information_content, NumberFormatUtils.phoneNumberSub(alicode));
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onChangeAlipay(alicode, aliname);
                }
            });
        } else {
            holder.setText(R.id.item_my_information_content, R.string.go_binding);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onBindAlipay();
                }
            });
        }
        holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
        holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
    }

    private void setWeChatItem(BaseViewHolder holder, String wechat_id) {
       // holder.setVisibility(R.id.item_my_information_prize_icon, !isEmpty(wechat_id) ? View.GONE : View.VISIBLE);
       // holder.setVisibility(R.id.item_my_information_prize_money, !isEmpty(wechat_id) ? View.GONE : View.VISIBLE);

        if (!isEmpty(wechat_id)) {
            holder.setText(R.id.item_my_information_content, wechat_id);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onWriteWeChatId(wechat_id);
                }
            });
        } else {
            holder.setText(R.id.item_my_information_prize_money, String.valueOf(20));
            holder.setText(R.id.item_my_information_content, R.string.go_input);
            holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
                if (l != null) {
                    l.onWriteWeChatId(wechat_id);
                }
            });
        }
    }


    private void setTaoBaoVerItem(BaseViewHolder holder, boolean isVer) {
        //holder.setVisibility(R.id.item_my_information_prize_icon, isVer ? View.GONE : View.VISIBLE);
       // holder.setVisibility(R.id.item_my_information_prize_money, isVer ? View.GONE : View.VISIBLE);
        holder.setViewChecked(R.id.item_my_information_sb, isVer);
        holder.setOnCheckedChangeListener(R.id.item_my_information_sb, (view, isChecked) -> {
            if (l != null) {
                l.onAuthorization(isChecked);
            }
        });
        //未授权
        if (!isVer) {
            holder.setText(R.id.item_my_information_prize_money, String.valueOf(10));
        }
    }

    private void setAboutWXTItem(BaseViewHolder holder, String verName) {
        holder.setVisibility(R.id.item_my_information_prize_icon, View.GONE);
        holder.setVisibility(R.id.item_my_information_prize_money, View.GONE);
        holder.setText(R.id.item_my_information_content, verName);
        holder.setViewOnClickListener(R.id.item_my_information_layout, v -> {
            if (l != null) {
                l.onAboutSuperman(verName);
            }
        });
    }

    @Override
    protected int getLayoutId(int viewType) {
        return R.layout.item_my_information;
    }


    private OnItemClikListener l;

    public void setOnItemClikListener(OnItemClikListener l) {
        this.l = l;
    }

    public interface OnItemClikListener {
        void onChangeHeadImg();

        void onShowUserId(String userId);

        void onChangeNickName(String nickName);

        void onBindNumber();

        void onSettingPassWord(boolean isSettingPassWord, String number);

        void onChangeNumber(String number);

        void onBindAlipay();

        void onChangeAlipay(String account, String name);

        void onWriteWeChatId(String wechat_id);

        void onAddressManagement();

        void onAuthorization(boolean isAllow);

        void onAboutSuperman(String verName);
    }
}
