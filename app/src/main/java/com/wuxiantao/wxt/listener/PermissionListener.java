package com.wuxiantao.wxt.listener;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/10/10 0010 1:07 8-19
 * Description: ${DESCRIPTION} 权限申请回掉接口
 * Author: Administrator Shiming-Shi
 */

public interface PermissionListener {

    /**
     * 权限申请成功
     */
    void  permissinSucceed();


    /**
     * 权限申请失败
     * @param permissions 未授权的权限
     */
    void  permissionFailing(String[] permissions);

}
