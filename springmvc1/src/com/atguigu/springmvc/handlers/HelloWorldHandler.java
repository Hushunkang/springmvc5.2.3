package com.atguigu.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月25日 13时41分19秒
 */
@Controller
public class HelloWorldHandler {

    /**
     * 1.使用@RequestMapping注解来映射请求的URL
     * 2.返回值会通过视图解析器解析为实际的物理视图
     * 对于InternalResourceViewResolver视图解析器而言，会做如下解析
     * prefix + returnVal + suffix这样的方式得到实际的物理视图，然后做请求转发操作
     * @return
     */
    @RequestMapping("/hello")
    public String hello(){
        return "success";
    }

}
