package com.wuxiantao.wxt.db;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.sqlite.WhereBuilder;
import org.xutils.ex.DbException;

import java.util.List;

/**
 * Copyright (C), 2018-2018, 都可信有限公司
 * Date: 2018/9/28 0028 20:50 8-19
 * Description: ${DESCRIPTION} 数据库管理
 * Author: Administrator Shiming-Shi
 */

public class DbManagement {

    private static volatile DbManagement  instance;

    private DbManager dbManager;

    private DbManagement(){
        //获取数据库管理器
//        dbManager = BaseApplication.getInstance().getDbManager();
    }

    public static DbManagement  newInstance(){
        if (instance == null){
            synchronized (DbManagement.class){
                if (instance == null){
                    instance = new DbManagement();
                }
            }
        }
        return instance;
    }


    /**
     * 存储数据
     * @param t
     * @param <T>
     * @return
     */
    public <T>boolean  isSave(T t){
        try {
            //实现数据的存储,配合User类中的注释才能进行对应的存储
            //表名和列名都是在User中注释决定的。
            //保存成功之后【会】对user的主键进行赋值绑定,并返回保存是否成功
            return dbManager.saveBindingId(t);
        }catch (DbException e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     *  存储数据
     * @param t
     * @param <T>
     * @return
     */
    public <T>void   save(T t){
        try {
            //实现数据的存储,配合User类中的注释才能进行对应的存储
            //表名和列名都是在User中注释决定的。
             dbManager.save(t);
        }catch (DbException e) {
            e.printStackTrace();
        }
    }


    /**
     * 删除数据
     * @param cla 数据原型
     * @param columnName 列名
     * @param op 条件
     * @param value 列所对应的值
     */
    public void delete(Class<?> cla,String columnName, String op, Object value){
        try {
            dbManager.delete(cla, WhereBuilder.b(columnName,op,value));
        }catch (DbException e){
            e.printStackTrace();
        }
    }

    /**
     * 删除数据
     * @param t 数据原型
     */
    public <T>void deleteAll(T t){
        try {
            dbManager.delete(t);
        }catch (DbException e){
            e.printStackTrace();
        }
    }


    /**
     * 删除数据
     * @param t 数据原型
     */
    public <T>void deleteAll(Class<T> t){
        try {
            dbManager.dropTable(t);
        }catch (DbException e){
            e.printStackTrace();
        }
    }


    /**
     * 删除数据
     * @param entityType
     * @param idValue
     */
    public void deleteById(Class<?> entityType, Object idValue){
        try {
            dbManager.deleteById(entityType,idValue);
        }catch (DbException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     * @param cla
     * @param key
     * @param obj
     * @param columnName
     * @param op
     * @param value
     */
    public void update(Class<?> cla,String key, Object obj,String columnName, String op, Object value){
        try {
            //要修改的数据，以键值对的显示传入,
            KeyValue keyValue = new KeyValue(key,obj);
            dbManager.update(cla, WhereBuilder.b(columnName,op,value),keyValue);
        }catch (DbException e){
            e.printStackTrace();
        }
    }

    /**
     * 修改数据
     * @param cla
     * @param key
     * @param obj
     */
    public void update(Class<?> cla,String key, Object obj){
        try {
            //要修改的数据，以键值对的显示传入,
            KeyValue keyValue = new KeyValue(key,obj);
            dbManager.update(cla, WhereBuilder.b("id","=",1),keyValue);
        }catch (DbException e){
            e.printStackTrace();
        }
    }


    /**
     * 查询所有数据
     * @param cla
     * @param <T>
     * @return
     */
    public <T> List<T> queryAll(Class<T> cla){
        try {
            return   dbManager.findAll(cla);
        }catch (DbException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  查询单个数据
     * @param entityType
     * @param idValue
     * @param <T>
     * @return
     */
    public <T> T query(Class<T> entityType,Object idValue){
        try {
           return dbManager.findById(entityType,idValue);
        }catch (DbException e){
            e.printStackTrace();
        }
        return null;
    }


    /**
     *  查询单个数据
     * @param entityType
     * @param <T>
     * @return
     */
    public <T> T query(Class<T> entityType){
        try {
            return dbManager.findById(entityType,1);
        }catch (DbException e){
            e.printStackTrace();
        }
        return null;
    }




    /**
     *  sql查询数据
     *   源生的sql语句
     * @return
     */
    public void execSQL(String sql){
        try {
            dbManager.execNonQuery(sql);
        }catch (DbException e){
            e.printStackTrace();
        }
    }



}
