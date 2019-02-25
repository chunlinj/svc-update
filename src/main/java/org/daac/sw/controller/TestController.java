package org.daac.sw.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by  HASEE on 2019-01-28
 */
@RestController
public class TestController {
    @RequestMapping("/welcome")
    public String welcome(){
        return "欢迎光临 版本1.0.8";
    }


    @RequestMapping("/port")
    public int welcome(HttpServletRequest request){
        return request.getServerPort();
    }
}
