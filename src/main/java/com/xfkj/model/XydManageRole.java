package com.xfkj.model;

import java.util.Date;
import java.util.List;

public class XydManageRole {
    private Integer id;

    private String roleName;

    private String remark;

    private String role;

    private String status;

    private Integer isDeleted;

    private Date createTime;

    private Date updateTime;
    //多余字段
    private Boolean pitchOn = false;
    private List<XydManageAccess> accesses;

    public List<XydManageAccess> getAccesses() {
        return accesses;
    }

    public void setAccesses(List<XydManageAccess> accesses) {
        this.accesses = accesses;
    }

    public Boolean getPitchOn() {
        return pitchOn;
    }

    public void setPitchOn(Boolean pitchOn) {
        this.pitchOn = pitchOn;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role == null ? null : role.trim();
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