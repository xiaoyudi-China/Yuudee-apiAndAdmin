package com.xfkj.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by King on 2018/9/26.
 */
@Component
@ConfigurationProperties()
@PropertySource("classpath:/properties/variable.properties")
public class Global {
    private String accessKeyId;
    private String accessKeySecret;
    private String registerMSM;
    private String registerWinMSM;
    private String remindMSM;
    private String loginMSM;
    private String flatMSM;
    private String bunchMSM;
    private String verbMSM;
    private String nounSenseMSM;
    private String nounMSM;
    private String updatePasswordMSM;
    private String newPhoneMSM;
    private String oldPhoneMSM;
    private String xydPassword;
    private int defaultAgeMax;
    private int defaultAgeMin;

    private String wregisterMSM;
    private String wregisterWinMSM;
    private String wremindMSM;
    private String wloginMSM;
    private String wflatMSM;
    private String wbunchMSM;
    private String wverbMSM;
    private String wnounSenseMSM;
    private String wnounMSM;
    private String wupdatePasswordMSM;
    private String wnewPhoneMSM;
    private String woldPhoneMSM;

    public String getWregisterMSM() {
        return wregisterMSM;
    }

    public void setWregisterMSM(String wregisterMSM) {
        this.wregisterMSM = wregisterMSM;
    }

    public String getWregisterWinMSM() {
        return wregisterWinMSM;
    }

    public void setWregisterWinMSM(String wregisterWinMSM) {
        this.wregisterWinMSM = wregisterWinMSM;
    }

    public String getWremindMSM() {
        return wremindMSM;
    }

    public void setWremindMSM(String wremindMSM) {
        this.wremindMSM = wremindMSM;
    }

    public String getWloginMSM() {
        return wloginMSM;
    }

    public void setWloginMSM(String wloginMSM) {
        this.wloginMSM = wloginMSM;
    }

    public String getWflatMSM() {
        return wflatMSM;
    }

    public void setWflatMSM(String wflatMSM) {
        this.wflatMSM = wflatMSM;
    }

    public String getWbunchMSM() {
        return wbunchMSM;
    }

    public void setWbunchMSM(String wbunchMSM) {
        this.wbunchMSM = wbunchMSM;
    }

    public String getWverbMSM() {
        return wverbMSM;
    }

    public void setWverbMSM(String wverbMSM) {
        this.wverbMSM = wverbMSM;
    }

    public String getWnounSenseMSM() {
        return wnounSenseMSM;
    }

    public void setWnounSenseMSM(String wnounSenseMSM) {
        this.wnounSenseMSM = wnounSenseMSM;
    }

    public String getWnounMSM() {
        return wnounMSM;
    }

    public void setWnounMSM(String wnounMSM) {
        this.wnounMSM = wnounMSM;
    }

    public String getWupdatePasswordMSM() {
        return wupdatePasswordMSM;
    }

    public void setWupdatePasswordMSM(String wupdatePasswordMSM) {
        this.wupdatePasswordMSM = wupdatePasswordMSM;
    }

    public String getWnewPhoneMSM() {
        return wnewPhoneMSM;
    }

    public void setWnewPhoneMSM(String wnewPhoneMSM) {
        this.wnewPhoneMSM = wnewPhoneMSM;
    }

    public String getWoldPhoneMSM() {
        return woldPhoneMSM;
    }

    public void setWoldPhoneMSM(String woldPhoneMSM) {
        this.woldPhoneMSM = woldPhoneMSM;
    }

    public int getDefaultAgeMax() {
        return defaultAgeMax;
    }

    public void setDefaultAgeMax(int defaultAgeMax) {
        this.defaultAgeMax = defaultAgeMax;
    }

    public int getDefaultAgeMin() {
        return defaultAgeMin;
    }

    public void setDefaultAgeMin(int defaultAgeMin) {
        this.defaultAgeMin = defaultAgeMin;
    }

    public String getOldPhoneMSM() {
        return oldPhoneMSM;
    }

    public void setOldPhoneMSM(String oldPhoneMSM) {
        this.oldPhoneMSM = oldPhoneMSM;
    }

    public String getNewPhoneMSM() {
        return newPhoneMSM;
    }

    public void setNewPhoneMSM(String newPhoneMSM) {
        this.newPhoneMSM = newPhoneMSM;
    }

    public String getXydPassword() {
        return xydPassword;
    }

    public void setXydPassword(String xydPassword) {
        this.xydPassword = xydPassword;
    }

    public String getRegisterWinMSM() {
        return registerWinMSM;
    }

    public void setRegisterWinMSM(String registerWinMSM) {
        this.registerWinMSM = registerWinMSM;
    }

    public String getFlatMSM() {
        return flatMSM;
    }

    public void setFlatMSM(String flatMSM) {
        this.flatMSM = flatMSM;
    }

    public String getBunchMSM() {
        return bunchMSM;
    }

    public void setBunchMSM(String bunchMSM) {
        this.bunchMSM = bunchMSM;
    }

    public String getVerbMSM() {
        return verbMSM;
    }

    public void setVerbMSM(String verbMSM) {
        this.verbMSM = verbMSM;
    }

    public String getNounSenseMSM() {
        return nounSenseMSM;
    }

    public void setNounSenseMSM(String nounSenseMSM) {
        this.nounSenseMSM = nounSenseMSM;
    }

    public String getNounMSM() {
        return nounMSM;
    }

    public void setNounMSM(String nounMSM) {
        this.nounMSM = nounMSM;
    }

    public String getUpdatePasswordMSM() {
        return updatePasswordMSM;
    }

    public void setUpdatePasswordMSM(String updatePasswordMSM) {
        this.updatePasswordMSM = updatePasswordMSM;
    }

    public String getLoginMSM() {
        return loginMSM;
    }

    public void setLoginMSM(String loginMSM) {
        this.loginMSM = loginMSM;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getRemindMSM() {
        return remindMSM;
    }

    public void setRemindMSM(String remindMSM) {
        this.remindMSM = remindMSM;
    }

    public String getRegisterMSM() {
        return registerMSM;
    }

    public void setRegisterMSM(String registerMSM) {
        this.registerMSM = registerMSM;
    }




}
