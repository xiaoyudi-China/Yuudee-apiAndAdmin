package com.xfkj.model;

import java.util.Date;
import java.util.List;

public class XydManageAccess {
    private Integer id;

    private String accessName;

    private String remark;

    private Integer sort;

    private String parentAccess;

    private Integer parentId;

    private String access;

    private String status;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;

    private List<XydManageAccess> submenu;

    public List<XydManageAccess> getSubmenu() {
        return submenu;
    }

    public void setSubmenu(List<XydManageAccess> submenu) {
        this.submenu = submenu;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessName() {
        return accessName;
    }

    public void setAccessName(String accessName) {
        this.accessName = accessName == null ? null : accessName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getParentAccess() {
        return parentAccess;
    }

    public void setParentAccess(String parentAccess) {
        this.parentAccess = parentAccess == null ? null : parentAccess.trim();
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access == null ? null : access.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
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