package com.xfkj;

import com.xfkj.common.config.Global;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by mai xiaogang on 2018/9/26.
 */
@Controller
@RequestMapping("/mxg")
public class MxgTest {
    @Autowired Global global;

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        System.out.print("user"+global.getAccessKeyId());
        return global.getAccessKeyId();
    }

}
