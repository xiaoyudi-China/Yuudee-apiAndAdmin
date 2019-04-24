package com.xfkj.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class XydManageAccount implements Serializable {
    private static final long serialVersionUID = -3819619483185197354L;
    private Integer id;

    private String accountName;

    private String account;

    private String password;

    private String phone;

    private String status;

    private Date createTime;

    private Date updateTime;

    private List<XydManageRole> roleList;

    public List<XydManageRole> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<XydManageRole> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName == null ? null : accountName.trim();
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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