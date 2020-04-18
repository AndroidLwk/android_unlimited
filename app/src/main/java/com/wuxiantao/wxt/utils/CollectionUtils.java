package com.wuxiantao.wxt.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:CollectionUtils
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-6-4 下午6:31
 * Description:${DESCRIPTION} 集合工具类
 */
public class CollectionUtils {

    //比较两个集合元素是否相同
    public static <T> boolean compareList(List<T> sourceList, List<T> targetList){
        if (sourceList == null || targetList == null){
            return false;
        }
        if (sourceList.size() != targetList.size()){
            return false;
        }
        Set<Integer> hashCodeSet = new HashSet<>();
        for (T t : sourceList){
            hashCodeSet.add(t.hashCode());
        }
        for (T t : targetList){
            if (!hashCodeSet.contains(t.hashCode())){
                return false;
            }
        }
        return true;
    }



}
