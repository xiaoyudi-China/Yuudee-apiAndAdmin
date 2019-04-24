package com.xfkj.model;

import java.util.Date;

public class XydChild {
    private Integer id;

    private Integer parentsId;

    private String phoneNumber;

    private String name;

    private String sex;

    private Date birthdate;

    private String birthTime;

    private String countiy;

    private String province;

    private String city;

    private String medical;

    private String medicalState;

    private String firstLanguage;

    private String firstRests;

    private String secondLanguage;

    private String secondRests;

    private String fatherCulture;

    private String motherCulture;

    private String trainingMethod;

    private String trainingRests;

    private String perfection;

    private Date createTime;

    private Date updateTime;

    private String status;

    private String photo;

    //冗余字段
    private String address;

    public String getAddress() {
        address = countiy+"-"+province+"-"+city;
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getBirthTime() {
        return birthTime;
    }

    public void setBirthTime(String birthTime) {
        this.birthTime = birthTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getParentsId() {
        return parentsId;
    }

    public void setParentsId(Integer parentsId) {
        this.parentsId = parentsId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getCountiy() {
        return countiy;
    }

    public void setCountiy(String countiy) {
        this.countiy = countiy == null ? null : countiy.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getMedical() {
        return medical;
    }

    public void setMedical(String medical) {
        this.medical = medical == null ? null : medical.trim();
    }

    public String getMedicalState() {
        return medicalState;
    }

    public void setMedicalState(String medicalState) {
        this.medicalState = medicalState == null ? null : medicalState.trim();
    }

    public String getFirstLanguage() {
        return firstLanguage;
    }

    public void setFirstLanguage(String firstLanguage) {
        this.firstLanguage = firstLanguage == null ? null : firstLanguage.trim();
    }

    public String getFirstRests() {
        return firstRests;
    }

    public void setFirstRests(String firstRests) {
        this.firstRests = firstRests == null ? null : firstRests.trim();
    }

    public String getSecondLanguage() {
        return secondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        this.secondLanguage = secondLanguage == null ? null : secondLanguage.trim();
    }

    public String getSecondRests() {
        return secondRests;
    }

    public void setSecondRests(String secondRests) {
        this.secondRests = secondRests == null ? null : secondRests.trim();
    }

    public String getFatherCulture() {
        return fatherCulture;
    }

    public void setFatherCulture(String fatherCulture) {
        this.fatherCulture = fatherCulture == null ? null : fatherCulture.trim();
    }

    public String getMotherCulture() {
        return motherCulture;
    }

    public void setMotherCulture(String motherCulture) {
        this.motherCulture = motherCulture == null ? null : motherCulture.trim();
    }

    public String getTrainingMethod() {
        return trainingMethod;
    }

    public void setTrainingMethod(String trainingMethod) {
        this.trainingMethod = trainingMethod == null ? null : trainingMethod.trim();
    }

    public String getTrainingRests() {
        return trainingRests;
    }

    public void setTrainingRests(String trainingRests) {
        this.trainingRests = trainingRests == null ? null : trainingRests.trim();
    }

    public String getPerfection() {
        return perfection;
    }

    public void setPerfection(String perfection) {
        this.perfection = perfection == null ? null : perfection.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}