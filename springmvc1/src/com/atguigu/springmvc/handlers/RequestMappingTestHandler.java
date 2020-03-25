package com.atguigu.springmvc.handlers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月25日 14时44分20秒
 */
@Controller
@RequestMapping("/requestMappingTest")
public class RequestMappingTestHandler {

    private static final String SUCCESS = "success";

    /**
     * 1.使用@RequestMapping注解来映射请求的URL
     * 2.返回值会通过视图解析器解析为实际的物理视图
     * 对于InternalResourceViewResolver视图解析器而言，会做如下解析
     * prefix + returnVal + suffix这样的方式得到实际的物理视图，然后做请求转发操作
     * 3.@RequestMapping注解除了可以用来修饰方法外，还可以用来修饰类
     * 1).类定义处:提供初步的请求映射信息，相对于WEB应用的根目录
     * 2).方法定义处:提供进一步细分的映射信息，相对于类定义处的URL
     *    若类定义处未标注@RequestMapping注解，则方法处标记的URL相对于WEB应用的根目录
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping(){
//        return RequestMappingTestHandler.SUCCESS;
        return SUCCESS;
    }

    /**
     * 使用method属性来指定请求的方式
     * 这里设置post类型，表明，我这个方法只接受post类型的请求，如果不是会响应405
     * HTTP Status 405 – 方法不允许
     * @return
     */
    @RequestMapping(value="/testRequestMethod",method=RequestMethod.POST)
    public String testRequestMethod(){
        return SUCCESS;
    }

    /**
     * 细节：同一个业务逻辑控制器里面，可以设置两个方法的@RequestMapping注解的value属性值是一样滴
     * 原理：因为它们不仅仅可以通过请求的url来区分，还可以通过请求的方式等来区分
     * @return
     */
    @RequestMapping(value="/testRequestMethod",method=RequestMethod.GET)
    public String testRequestMethod(Integer userId){
        return SUCCESS;
    }

    /**
     * 了解一哈：可以使用params属性和headers属性来更加精确标识请求映射
     *          params属性和headers属性还可以支持简单的表达式
     * @return
     */
    @RequestMapping(value="/testParamsAndHeaders",params={"userId","age=18"}/*,headers={"Accept-Language=zh-CN,zh;q=0.9"}*/)
    public String testParamsAndHeaders(){
        return SUCCESS;
    }

    /**
     * 测试Ant风格的资源地址
     * @return
     */
    @RequestMapping(value="/testAntResourceUrl/*/abc")
    public String testAntResourceUrl(){
        return SUCCESS;
    }

    /**
     * 测试@PathVariable注解：可以用来映射URL中的占位符到业务方法的参数中
     * 说明：spring mvc正是因为有此特性，才使得其支持rest风格的url
     * @return
     */
    @RequestMapping(value="/testPathVariable/{userId}")
    public String testPathVariable(@PathVariable(value="userId")Integer userId){
        System.out.println("testPathVariable:" + userId);
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url get请求 表示查询
     * @return
     */
    @RequestMapping(value="/testRest/{userId}",method=RequestMethod.GET)
    public String testRestGet(@PathVariable(value="userId")Integer userId){
        System.out.println("testRest get请求:" + userId);
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url post请求 表示新增
     * @return
     */
    @RequestMapping(value="/testRest",method=RequestMethod.POST)
    public String testRestPost(){
        System.out.println("testRest post请求");
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url delete请求 表示删除
     * @return
     */
    @RequestMapping(value="/testRest/{userId}",method=RequestMethod.DELETE)
    public String testRestDelete(@PathVariable(value="userId")Integer userId){
        System.out.println("testRest delete请求:" + userId);
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url put请求 表示更新
     * @return
     */
    @RequestMapping(value="/testRest/{userId}",method=RequestMethod.PUT)
    public String testRestPut(@PathVariable(value="userId")Integer userId){
        System.out.println("testRest put请求:" + userId);
        return SUCCESS;
    }

    /**
     * 总结Rest风格的url：
     * Rest风格的url，以crud为例
     * 新增： /order POST
     * 删除: /order/1 DELETE delete?id=1
     * 修改: /order/1 PUT update?id=1
     * 获取: /order/1 GET get?id=1
     *
     * 如何发送DELETE请求或者PUT请求呢？
     * 1.需要在web.xml中配置HiddenHttpMethodFilter过滤器
     * 2.需要发送POST请求
     * 3.需要在发送POST请求时携带一个name="_method"的隐藏域，值为DELETE或者PUT
     *
     * 在spring mvc的目标方法中如何得到id呢？
     * 使用@PathVariable注解
     */

}
