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
@RequestMapping("/helloWorld")
public class HelloWorldHandler {

    @RequestMapping("/hello")
    public String hello(){
        return "success";
    }

}
