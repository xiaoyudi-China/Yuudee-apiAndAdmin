package com.xfkj.common.utils;

import java.io.*;
import java.util.Date;

/**
 * 写入日志
 * filePath 日志文件的路径
 * code 要写入日志文件的内容
 */
public class PrintToFile {
    /**
     * 写入日志 filePath 日志文件的路径 code 要写入日志文件的内容
     */
    public static  boolean print(String code) {
        try {
            String filePath = "/usr/local/tomcat/tomcat8080/logs/mxg.log";
            File tofile = new File(filePath);
            FileWriter fw = new FileWriter(tofile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String time = DateUtil.getDate();
            System.out.println(time+":"+code);
            pw.println(time+":"+code);
            pw.close();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * 写入日志 filePath 日志文件的路径 code 要写入日志文件的内容
     */
    public static  boolean printPlay(String code) {
        try {
            String filePath = "/usr/local/tomcat/tomcat8080/logs/mxg.log";
            File tofile = new File(filePath);
            FileWriter fw = new FileWriter(tofile, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            String time = DateUtil.getDate();
            System.out.println(time+":"+code);
            pw.println(time+":"+code);
            pw.close();
            bw.close();
            fw.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }
    public static void main(String[] args){
    }
}
