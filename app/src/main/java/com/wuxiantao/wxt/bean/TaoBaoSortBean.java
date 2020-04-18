package com.wuxiantao.wxt.bean;

import java.util.List;

/**
 * Company:成都可信网络科技有限责任公司
 * FileName:TaoBaoSortBean
 * Author:android
 * Mail:2898682029@qq.com
 * Date:19-7-17 下午4:40
 * Description:${DESCRIPTION} 淘宝分类
 */
public class TaoBaoSortBean {


    /**
     * id : 1
     * cate_id : 50025705,21,50016349,50016348,28,29,50468001,50002768
     * cate_title : 百货
     * pid : 0
     * cate_img : http://ddj.haowusong.com/static/upload/726360ca378c449c/9037233d1e89b8b9.jpg
     * sort : 1
     * status : 1
     * is_deleted : 0
     * create_at : 2019-05-06 21:25:08
     * sub : []
     */

    private int id;
    private String cate_id;
    private String cate_title;
    private int pid;
    private String cate_img;
    private int sort;
    private int status;
    private int is_deleted;
    private String create_at;
    private List<?> sub;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCate_title() {
        return cate_title;
    }

    public void setCate_title(String cate_title) {
        this.cate_title = cate_title;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getCate_img() {
        return cate_img;
    }

    public void setCate_img(String cate_img) {
        this.cate_img = cate_img;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public String getCreate_at() {
        return create_at;
    }

    public void setCreate_at(String create_at) {
        this.create_at = create_at;
    }

    public List<?> getSub() {
        return sub;
    }

    public void setSub(List<?> sub) {
        this.sub = sub;
    }
}
