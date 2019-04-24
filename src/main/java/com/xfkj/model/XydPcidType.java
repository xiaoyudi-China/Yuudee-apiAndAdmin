package com.xfkj.model;

import java.io.Serializable;
import java.util.Date;

public class XydPcidType implements Serializable{
    private static final long serialVersionUID = -4514463804429782623L;
    private Integer id;

    private String isOptional;

    private Integer nameEnum;

    private String name;

    private Integer sort;

    private String title;

    private String states;

    private Date createTime;

    private Date updateTime;

    public Integer getNameEnum() {
        return nameEnum;
    }

    public void setNameEnum(Integer nameEnum) {
        this.nameEnum = nameEnum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIsOptional() {
        return isOptional;
    }

    public void setIsOptional(String isOptional) {
        this.isOptional = isOptional == null ? null : isOptional.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}