package com.xfkj.common.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mai xiaogang on 2018/11/19.
 * 手机号格式效验
 */
public class PhoneCheckUtil {
    /**国内手机号校验
     * 移动号段：139 138 137 136 135 134 147 150 151 152 157 158 159 178 182 183 184 187 188 198
     联通号段：130 131 132 155 156 166 185 186 145 176
     电信号段：133 153 177 173 180 181 189 199
     虚拟运营商号段：170 171
     */
    public static boolean phoneCheck(String phone) {
        if (null == phone || "".equals(phone)) {
            return false;
        }else {
            if (phone.length() != 11) {
                return false;
            }
            String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9])|(16[6]))\\d{8}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public static boolean foreigPhoneCheck(String phone) {
        if (null == phone || "".equals(phone)) {
            return false;
        }else {
            if (phone.length() < 1 || phone.length() > 15) {
                return false;
            }
            String regex = "^[0-9]\\d*$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            if (isMatch) {
                return true;
            }
        }
        return false;
    }

    public static boolean phoneTypeCheck(String phone, String type) {
        //国内校验
       if ("86".equals(type)){
          return phoneCheck(phone);
       }else {
           return foreigPhoneCheck(phone);
       }
    }

    public static void main(String[] args) {
        boolean flag = PhoneCheckUtil.phoneTypeCheck("755-86285739", "81");
        System.out.println(flag);
    }
}
