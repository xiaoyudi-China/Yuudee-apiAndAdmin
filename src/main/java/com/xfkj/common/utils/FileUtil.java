package com.xfkj.common.utils;

import java.io.*;


/**
 * 
 * File operations
 * 
 * @copyright (C) CCWOnline 2009 All Rights Reserved
 * 
 * $Author: gt $ $Revision: 1.1 $ $Date: 2009/05/13 15:41:00 $
 * 
 */

public class FileUtil {
	/**
	 * 删除文件夹
	 * @param file
	 */
	public static void deleteFile(File file){
		if(file.exists()){                    //判断文件是否存在
			if(file.isFile()){                    //判断是否是文件
				file.delete();                       //delete()方法 你应该知道 是删除的意思;
			}else if(file.isDirectory()){              //否则如果它是一个目录
				File files[] = file.listFiles();               //声明目录下所有的文件 files[];
				for(int i=0;i<files.length;i++){            //遍历目录下所有的文件
					FileUtil.deleteFile(files[i]);             //把每个文件 用这个方法进行迭代
				} 
			} 
			file.delete(); 
	    }else{ 
	    	System.out.println("所删除的文件不存在！"+'\n');
	    } 
	} 
	/**
	 * 删除文件夹
	 * @param file
	 */
	public static void deleteFile(String dir){
		FileUtil.deleteFile(new File(dir));
	} 
	/**
	 * 将内存的数据保存到指定文件
	 * @param pathName 全路径名称 如： D:\\abc\123.txt
	 * @param memoryData 要保存到文件的内存数据 
	 */
	public static void saveFileFromMemory(String pathName, String memoryData){
		BufferedWriter fw = null;
		FileWriter fileOut = null;
		try {
			File file = new File(pathName);
			fileOut = new FileWriter(file);
			fw = new BufferedWriter(fileOut);
			fw.write(new String(memoryData.getBytes("ISO-8859-1")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(fileOut != null)
				try {
					fileOut.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 将内存的数据保存到指定文件
	 * @param pathName 全路径名称 如： D:\\abc\123.txt
	 * @param memoryData 要保存到文件的内存数据 
	 */
	public static void saveBytesFileFromMemory(String pathName, String memoryData){
		FileUtil.deleteFile(pathName);
		BufferedWriter fw = null;
		FileOutputStream out = null;
		try {
			File file = new File(pathName);
			out=new FileOutputStream(file,true);
			out.write(memoryData.getBytes("utf-8"));
//			fw.write(new String(memoryData.getBytes("UTF-8")));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(out != null)
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	
	public static String filterForbidChator(String pathName){
		return pathName.replaceAll("[/:*<>|]+", "");
	}
	/**
	 * save file
	 * @author gt
	 * @param is :
	 * 			InputStream
	 * @param targetFile :
	 * 			target file path
	 * @param length
	 *			length of file
	 */
    public static File saveFile(InputStream is, String targetFile, int maxLength) throws Exception {
		
    	try{
    		File file = new File(targetFile);
    		if(file.exists() == false)
    			file.createNewFile();
    		OutputStream os = new FileOutputStream(file);
    		int byteRead=0;
    		byte[] buffer=new byte[1024];
    		
    		int count = 0;
    		while((byteRead = is.read(buffer, 0, 1024)) != -1){
    			count += byteRead;
    			if(count > maxLength){
    				os.close();
    				is.close();
    				file.delete();
    				throw new Exception("File length over maxLength");
    			}
    			
    			os.write(buffer, 0, byteRead);
    		}
    		os.close();
    		is.close();
    		return file;
    	}
    	catch(Exception e){
    		throw e;
    	}
	}
    /**
     * get upload path,easy to debug
     * @return
     * 		upload path
     */
    public static String getUploadPath(){
    	return "";
    }
    /**
     * create folder if the folder is not exist
     * @param userName:
     * 				current operator's userName
     * @return
     * 				folder path
     */
    public static String createFolder(String userName) throws Exception {
    	String uploadPath=getUploadPath();
    	try{
    		File file=new File(uploadPath+"/"+userName);
    		if(!file.exists())
    			file.mkdir();
    		return file.getAbsolutePath()+"/";
    	}
    	catch(Exception e){
    		throw e;
    	}    	
    }
    /**
     * remove file
     * @param path:
     * 			file path
     */
	public static void remove(String path) {
		
		File f= new File(path);
		if(f.exists())
			f.delete();
		
	}
	public static void createFile(String path, String content) {
	      String s = new String();
	      String s1 = new String();
	      try {
	       File f = new File(path);
	       if (f.exists()) {
	        System.out.println("文件存在");
	       } else {
	        System.out.println("文件不存在，正在创建...");
	        if (f.createNewFile()) {
	         System.out.println("文件创建成功！");
	        } else {
	         System.out.println("文件创建失败！");
	        }

	       }
	       BufferedReader input = new BufferedReader(new FileReader(f));

	       while ((s = input.readLine()) != null) {
	        s1 += s + "\n";
	       }
	       System.out.println("文件内容：" + s1);
	       input.close();
	       s1 += content;

	       BufferedWriter output = new BufferedWriter(new FileWriter(f));
	       output.write(s1);
	       output.close();
	      } catch (Exception e) {
	       e.printStackTrace();
	      }
	}
       
    /**  
     * 复制单个文件的工具文法  
     * @param srcFilePath  
     * @param destFilePath  
     * @throws IOException
     */  
    public static void copyFile (String srcFilePath, String destFilePath) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        byte[] buffer = null;   
        try {   
            fis = new FileInputStream(srcFilePath);
            fos = new FileOutputStream(destFilePath);
            buffer = new byte[1024];   
            int count = 0;   
            while ((count = fis.read(buffer)) != -1) {   
                fos.write(buffer, 0, buffer.length);   
            }   
        } finally {   
            if (fis != null)   
                fis.close();   
            if (fos != null)    
                fos.close();   
            if (buffer != null)    
                buffer = null;   
        }   
    }
    
    public static void mkdirs(String folderPath){
    	File folder = new File(folderPath);
    	if(!folder.exists())
    		folder.mkdirs();
    }
    
	public static File saveFile(InputStream is, String targetFile) throws Exception {
		try {
			File file = new File(targetFile);
			if (!file.exists())
				file.createNewFile();
			OutputStream os = new FileOutputStream(file);
			int byteRead = 0;
			byte[] buffer = new byte[1024];

			while ((byteRead = is.read(buffer, 0, 1024)) != -1) {
				os.write(buffer, 0, byteRead);
			}
			os.close();
			is.close();
			return file;
		} catch (Exception e) {
			throw e;
		}
	}
}

