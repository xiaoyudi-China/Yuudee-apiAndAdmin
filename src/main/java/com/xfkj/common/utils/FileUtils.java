package com.xfkj.common.utils;

import ch.qos.logback.classic.pattern.SyslogStartConverter;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.apache.commons.fileupload.disk.DiskFileItem;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

import java.util.*;

/**
 * Created by King on 2018/10/18.
 */
public class FileUtils {
    // endpoint是访问OSS的域名。如果您已经在OSS的控制台上 创建了Bucket，请在控制台上查看域名。
    private static String endpoint = "http://oss-cn-beijing.aliyuncs.com";

    private static String endpoint1 = "http://yuudee.oss-cn-beijing.aliyuncs.com";
    private static String accessKeyId = "LTAIEwm2LXa64cdB";
    private static String accessKeySecret = "6VW6F1l7GcZiYvrWWbnNzxdiEwiDyW";
    private static String bucketName = "yuudee";

    /**
     * 上传文件
     *
     * @param request
     * @return map
     */
    public static Map<String, Object> upload(HttpServletRequest request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String extendFileName = ".jpg";
        if (multipartResolver.isMultipart(request)) {
            try {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                List<MultipartFile> images = multiRequest.getFiles("image");// 接收的图片
                if (null != images && images.size()>0) {
                    ArrayList<String> strings = addFile(images, extendFileName);
                    if (strings.size() > 0) {
                        mapReturn.put("images", strings);
                    }
                }
            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }


    /**
     * 富文本上传文件方法
     *
     * @param request
     * @return map
     */
    public static Map<String, Object> uploadfile(MultipartFile request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);

        String extendFileName = ".jpg";
        if (true) {
            try {
                List<MultipartFile> images = new ArrayList<>();// 接收的图片
                images.add(request);
                if (null != images && images.size()>0) {
                    ArrayList<String> strings = addFile(images, extendFileName);
                    if (strings.size() > 0) {
                        mapReturn.put("images", strings);
                    }
                }
            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }

    public static Map<String, Object> verbimages(HttpServletRequest request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String extendFileName = ".jpg";
        if (multipartResolver.isMultipart(request)) {
            try {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;

                List<MultipartFile> verbImage = multiRequest.getFiles("verbImage");// 接收的图片
                if (verbImage.size() > 0) {
                    ArrayList<String> strings = addFile(verbImage, extendFileName);
                    if (strings.size() > 0) {
                        mapReturn.put("verbImage", strings);
                    }
                }

                List<MultipartFile> verbRecord = multiRequest.getFiles("verbRecord");// 接收的录音
                if (verbRecord.size() > 0) {
                    ArrayList<String> strings1 = addFile(verbRecord, extendFileName);
                    if (strings1.size() > 0) {
                        mapReturn.put("verbRecord", strings1);
                    }
                }

                List<MultipartFile> cardImage = multiRequest.getFiles("cardImage");// 接收的图片
                if (cardImage.size() > 0) {
                    ArrayList<String> strings2 = addFile(cardImage, extendFileName);
                    if (strings2.size() > 0) {
                        mapReturn.put("cardImage", strings2);
                    }
                }


                List<MultipartFile> cardRecord = multiRequest.getFiles("cardRecord");// 接收的图片
                if (cardRecord.size() > 0) {
                    ArrayList<String> strings7 = addFile(cardRecord, extendFileName);
                    if (strings7.size() > 0) {
                        mapReturn.put("cardRecord", strings7);
                    }
                }

                List<MultipartFile> startSlideshow = multiRequest.getFiles("startSlideshow");// 接收的图片
                if (startSlideshow.size() > 0) {
                    ArrayList<String> strings3 = addFile(startSlideshow, extendFileName);
                    if (strings3.size() > 0) {
                        mapReturn.put("startSlideshow", strings3);
                    }
                }

                List<MultipartFile> endSlideshow = multiRequest.getFiles("endSlideshow");// 接收的图片
                if (endSlideshow.size() > 0) {
                    ArrayList<String> strings4 = addFile(endSlideshow, extendFileName);
                    if (strings4.size() > 0) {
                        mapReturn.put("endSlideshow", strings4);
                    }
                }

                List<MultipartFile> groupImage = multiRequest.getFiles("groupImage");// 接收的图片
                if (groupImage.size() > 0) {
                    ArrayList<String> strings5 = addFile(groupImage, extendFileName);
                    if (strings5.size() > 0) {
                        mapReturn.put("groupImage", strings5);
                    }
                }

                List<MultipartFile> groupRecord = multiRequest.getFiles("groupRecord");// 接收的图片
                if (groupRecord.size() > 0) {
                    ArrayList<String> strings6 = addFile(groupRecord, extendFileName);
                    if (strings6.size() > 0) {
                        mapReturn.put("groupRecord", strings6);
                    }
                }

            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }

    public static Map<String, Object> nounImages(HttpServletRequest request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String extendFileName = ".jpg";
        if (multipartResolver.isMultipart(request)) {
            try {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取Http ContentType
                String contentType = multiRequest.getContentType();
                if (contentType.equals("image/jpeg") || contentType.equals("image/pjpeg")) {
                    // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
                    extendFileName = ".jpg";
                } else if (contentType.equals("image/png") || contentType.equals("image/x-png")) {
                    // IE6上传的png图片的headimageContentType是"image/x-png"
                    extendFileName = ".png";
                }
                List<MultipartFile> wireImage = multiRequest.getFiles("wireImage");// 接收的图片
                if (wireImage.size() > 0) {
                    ArrayList<String> strings = addFile(wireImage, extendFileName);
                    if (strings.size() > 0) {
                        mapReturn.put("wireImage", strings);
                    }
                }
                List<MultipartFile> wireRecord = multiRequest.getFiles("wireRecord");// 接收的图片
                if (wireRecord.size() > 0) {
                    ArrayList<String> strings1 = addFile(wireRecord, extendFileName);
                    if (strings1.size() > 0) {
                        mapReturn.put("wireRecord", strings1);
                    }
                }
                List<MultipartFile> groupImage = multiRequest.getFiles("groupImage");// 接收的图片
                if (groupImage.size() > 0) {
                    ArrayList<String> strings2 = addFile(groupImage, extendFileName);
                    if (strings2.size() > 0) {
                        mapReturn.put("groupImage", strings2);
                    }
                }

                List<MultipartFile> colorPenRecord = multiRequest.getFiles("colorPenRecord");// 接收的图片
                if (colorPenRecord.size() > 0) {
                    ArrayList<String> strings5 = addFile(colorPenRecord, extendFileName);
                    if (strings5.size() > 0) {
                        mapReturn.put("colorPenRecord", strings5);
                    }
                }

                List<MultipartFile> groupRecord = multiRequest.getFiles("groupRecord");// 接收的图片
                if (groupRecord.size() > 0) {
                    ArrayList<String> strings3 = addFile(groupRecord, extendFileName);
                    if (strings3.size() > 0) {
                        mapReturn.put("groupRecord", strings3);
                    }
                }
            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }

    public static Map<String, Object> sentenceGroup(HttpServletRequest request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String extendFileName = ".jpg";
        if (multipartResolver.isMultipart(request)) {
            try {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                // 获取Http ContentType
                String contentType = multiRequest.getContentType();
                if (contentType.equals("image/jpeg") || contentType.equals("image/pjpeg")) {
                    // IE6上传jpg图片的headimageContentType是image/pjpeg，而IE9以及火狐上传的jpg图片是image/jpeg
                    extendFileName = ".jpg";
                } else if (contentType.equals("image/png") || contentType.equals("image/x-png")) {
                    // IE6上传的png图片的headimageContentType是"image/x-png"
                    extendFileName = ".png";
                }
                List<MultipartFile> startSlideshow = multiRequest.getFiles("startSlideshow");// 接收的图片
                ArrayList<String> strings = addFile(startSlideshow, extendFileName);
                if (strings.size() > 0) {
                    mapReturn.put("startSlideshow", strings);
                }
                List<MultipartFile> cardOneImage = multiRequest.getFiles("cardOneImage");// 接收的图片
                if (cardOneImage.size() > 0) {
                    ArrayList<String> strings1 = addFile(cardOneImage, extendFileName);
                    if (strings1.size() > 0) {
                        mapReturn.put("cardOneImage", strings1);
                    }
                }

                List<MultipartFile> cardoneRecord = multiRequest.getFiles("cardOneRecord");// 接收的图片
                if (cardoneRecord.size() > 0) {
                    ArrayList<String> strings2 = addFile(cardoneRecord, extendFileName);
                    if (strings2.size() > 0) {
                        mapReturn.put("cardOneRecord", strings2);
                    }
                }

                List<MultipartFile> cardTwoImage = multiRequest.getFiles("cardTwoImage");// 接收的图片
                if (cardTwoImage.size() > 0) {
                    ArrayList<String> strings3 = addFile(cardTwoImage, extendFileName);
                    if (strings3.size() > 0) {
                        mapReturn.put("cardTwoImage", strings3);
                    }
                }

                List<MultipartFile> cardTwoRecord = multiRequest.getFiles("cardTwoRecord");// 接收的图片
                if (cardTwoRecord.size() > 0) {
                    ArrayList<String> strings4 = addFile(cardTwoRecord, extendFileName);
                    if (strings4.size() > 0) {
                        mapReturn.put("cardTwoRecord", strings4);
                    }
                }

                List<MultipartFile> groupRecord = multiRequest.getFiles("groupRecord");// 接收的图片
                if (groupRecord.size() > 0) {
                    ArrayList<String> strings5 = addFile(groupRecord, extendFileName);
                    if (strings5.size() > 0) {
                        mapReturn.put("groupRecord", strings5);
                    }
                }
                List<MultipartFile> groupImage = multiRequest.getFiles("groupImage");// 接收的图片
                if (groupRecord.size() > 0) {
                    ArrayList<String> strings6 = addFile(groupImage, extendFileName);
                    if (strings6.size() > 0) {
                        mapReturn.put("groupImage", strings6);
                    }
                }

            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }

    public static Map<String, Object> sentenceReslove(HttpServletRequest request) {
        Map<String, Object> mapReturn = new HashMap<String, Object>();
        // 生成OSSClient，您可以指定一些参数，详见“SDK手册 > Java-SDK > 初始化”，
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        String extendFileName = ".jpg";
        if (multipartResolver.isMultipart(request)) {
            try {
                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
                List<MultipartFile> startSlideshow = multiRequest.getFiles("startSlideshow");// 接收的图片
                if (startSlideshow.size() > 0) {
                    ArrayList<String> strings = addFile(startSlideshow, extendFileName);
                    if (strings.size() > 0) {
                        mapReturn.put("startSlideshow", strings);
                    }
                }

                List<MultipartFile> cardOneImage = multiRequest.getFiles("cardOneImage");// 接收的图片
                if (cardOneImage.size() > 0) {
                    ArrayList<String> strings1 = addFile(cardOneImage, extendFileName);
                    if (strings1.size() > 0) {
                        mapReturn.put("cardOneImage", strings1);
                    }
                }

                List<MultipartFile> cardoneRecord = multiRequest.getFiles("cardOneRecord");// 接收的图片
                if (cardoneRecord.size() > 0) {
                    ArrayList<String> strings2 = addFile(cardoneRecord, extendFileName);
                    if (strings2.size() > 0) {
                        mapReturn.put("cardOneRecord", strings2);
                    }
                }

                List<MultipartFile> cardTwoImage = multiRequest.getFiles("cardTwoImage");// 接收的图片
                if (cardTwoImage.size() > 0) {
                    ArrayList<String> strings3 = addFile(cardTwoImage, extendFileName);
                    if (strings3.size() > 0) {
                        mapReturn.put("cardTwoImage", strings3);
                    }
                }

                List<MultipartFile> cardTwoRecord = multiRequest.getFiles("cardTwoRecord");// 接收的图片
                if (cardTwoRecord.size() > 0) {
                    ArrayList<String> strings4 = addFile(cardTwoRecord, extendFileName);
                    if (strings4.size() > 0) {
                        mapReturn.put("cardTwoRecord", strings4);
                    }
                }

                List<MultipartFile> cardThreeRecord = multiRequest.getFiles("cardThreeRecord");// 接收的图片
                if (cardThreeRecord.size() > 0) {
                    ArrayList<String> strings5 = addFile(cardThreeRecord, extendFileName);
                    if (strings5.size() > 0) {
                        mapReturn.put("cardThreeRecord", strings5);
                    }
                }

                List<MultipartFile> cardThreeImage = multiRequest.getFiles("cardThreeImage");// 接收的图片
                if (cardThreeImage.size() > 0) {
                    ArrayList<String> strings6 = addFile(cardThreeImage, extendFileName);
                    if (strings6.size() > 0) {
                        mapReturn.put("cardThreeImage", strings6);
                    }
                }

                List<MultipartFile> cardFourRecord = multiRequest.getFiles("cardFourRecord");// 接收的图片
                if (cardFourRecord.size() > 0) {
                    ArrayList<String> strings7 = addFile(cardFourRecord, extendFileName);
                    if (strings7.size() > 0) {
                        mapReturn.put("cardFourRecord", strings7);
                    }
                }

                List<MultipartFile> cardFourImage = multiRequest.getFiles("cardFourImage");// 接收的图片
                if (cardFourImage.size() > 0) {
                    ArrayList<String> strings8 = addFile(cardFourImage, extendFileName);
                    if (strings8.size() > 0) {
                        mapReturn.put("cardFourImage", strings8);
                    }
                }

                List<MultipartFile> groupRecord = multiRequest.getFiles("groupRecord");// 接收的图片
                if (groupRecord.size() > 0) {
                    ArrayList<String> strings9 = addFile(groupRecord, extendFileName);
                    if (strings9.size() > 0) {
                        mapReturn.put("groupRecord", strings9);
                    }
                }
            } catch (OSSException oe) {
                oe.printStackTrace();
            } catch (ClientException ce) {
                ce.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                ossClient.shutdown();
            }
        }
        return mapReturn;
    }

    public static ArrayList<String> addFile(List<MultipartFile> parameter, String extendFileName) {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        ArrayList<String> imagesinfos = new ArrayList<>();
        try {
            if (parameter != null) {
                ArrayList<String> imagesinfo = new ArrayList<>();
                for (MultipartFile multipartFile : parameter) {
                    String originalFilename = multipartFile.getOriginalFilename();
                    if (IsObjectNullUtils.is(originalFilename)) {
                        continue;
                    }
                    String[] split = originalFilename.split("\\.");
                    // 文件存储入OSS，Object的名称为uuid
                    String uuid = FileUtils.uuid();
                    File f = null;
                    f = File.createTempFile("tmp", null);
                    multipartFile.transferTo(f);
                    imagesinfo.add(endpoint1 + "/" + uuid + "." + split[1]);
                    ossClient.putObject(bucketName, uuid + "." + split[1], f);
                }
                return imagesinfo;
            }
        } catch (Exception e) {

        }
        return imagesinfos;
    }


    private static File multipartToFile(MultipartFile multfile) {
        CommonsMultipartFile cf = (CommonsMultipartFile) multfile;
        // 这个myfile是MultipartFile的
        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
        File file = fi.getStoreLocation();
        // 手动创建临时文件
        if (file.length() < 20971520) {
            File tmpFile = new File(
                    System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + file.getName());
            try {
                multfile.transferTo(tmpFile);
            } catch (IllegalStateException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return new File(tmpFile.getAbsolutePath());
        }
        return file;
    }

    /**
     * 封装JDK自带的UUID, 通过Random数字生成, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    public static boolean deleteFile(String filePath) {
        if (IsObjectNullUtils.is(filePath))
            return false;
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        boolean exist = ossClient.doesObjectExist(bucketName, filePath);
        if (!exist) {
            System.out.println("文件不存在,filePath="+ filePath);
            return false;
        }
        System.out.println("删除文件,filePath="+filePath);
        ossClient.deleteObject(bucketName, filePath);
        ossClient.shutdown();
        return true;
    }
}
