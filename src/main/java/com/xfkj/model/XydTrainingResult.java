package com.xfkj.model;

import java.util.Date;

public class XydTrainingResult {
    private Integer id;

    private Integer userId;

    private String name;

    private Integer coursewareId;

    private Integer groupId;

    private String scene;

    private String module;

    private String childMoudle;

    private Date startTime;

    private Date endTime;

    private Integer stayTime;

    private String pass;

    private String disturbName;

    private String errorType;

    private Date createTime;

    private Date updateTime;

    private String states;

    private String stayTimeList;

    private Integer count;

    //冗余字段

    private Integer testPersonCount=0;//测试人数

    private Integer testCount=0;//训练次数

    private Double passCount=0.00;//通过率

    private String avgTestTime="";//平均测试时长

    private Double duration1;

    private Double duration2;

    private Double duration3;

    private Double duration4;

    public Double getDuration1() {
        return duration1;
    }

    public void setDuration1(Double duration1) {
        this.duration1 = duration1;
    }

    public Double getDuration2() {
        return duration2;
    }

    public void setDuration2(Double duration2) {
        this.duration2 = duration2;
    }

    public Double getDuration3() {
        return duration3;
    }

    public void setDuration3(Double duration3) {
        this.duration3 = duration3;
    }

    public Double getDuration4() {
        return duration4;
    }

    public void setDuration4(Double duration4) {
        this.duration4 = duration4;
    }

    public double getPassCount() {
        return passCount;
    }

    public void setPassCount(double passCount) {
        this.passCount = passCount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getTestPersonCount() {
        return testPersonCount;
    }

    public void setTestPersonCount(Integer testPersonCount) {
        this.testPersonCount = testPersonCount;
    }

    public Integer getTestCount() {
        return testCount;
    }

    public void setTestCount(Integer testCount) {
        this.testCount = testCount;
    }


    public String getAvgTestTime() {
        return avgTestTime;
    }

    public void setAvgTestTime(String avgTestTime) {
        this.avgTestTime = avgTestTime;
    }

    public String getChildMoudle() {
        return childMoudle;
    }

    public void setChildMoudle(String childMoudle) {
        this.childMoudle = childMoudle;
    }

    public String getStayTimeList() {
        return stayTimeList;
    }

    public void setStayTimeList(String stayTimeList) {
        this.stayTimeList = stayTimeList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getCoursewareId() {
        return coursewareId;
    }

    public void setCoursewareId(Integer coursewareId) {
        this.coursewareId = coursewareId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getScene() {
        return scene;
    }

    public void setScene(String scene) {
        this.scene = scene == null ? null : scene.trim();
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module == null ? null : module.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getStayTime() {
        return stayTime;
    }

    public void setStayTime(Integer stayTime) {
        this.stayTime = stayTime;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getDisturbName() {
        return disturbName;
    }

    public void setDisturbName(String disturbName) {
        this.disturbName = disturbName == null ? null : disturbName.trim();
    }

    public String getErrorType() {
        return errorType;
    }

    public void setErrorType(String errorType) {
        this.errorType = errorType == null ? null : errorType.trim();
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

    public String getStates() {
        return states;
    }

    public void setStates(String states) {
        this.states = states == null ? null : states.trim();
    }
}