package com.xfkj.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by mai xiaogang on 2018/11/2.
 * 文件操作、量分数据
 * 将excel数据导入库里
 */
@Controller
@RequestMapping(value = "/manage/file")
public class FileContoller {

    @RequestMapping(value = "/import")
    public Map inportFile(HttpServletRequest request){
        Map<String, Object> result = new HashMap();
        return result;
    }
}
