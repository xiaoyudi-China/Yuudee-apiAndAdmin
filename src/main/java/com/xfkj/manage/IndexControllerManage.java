package com.xfkj.manage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by King on 2018/11/9.
 */
@Controller
@RequestMapping("/")
public class IndexControllerManage {
    @RequestMapping("")
    public String index(){
        return "/demo/system/login";
    }
}
