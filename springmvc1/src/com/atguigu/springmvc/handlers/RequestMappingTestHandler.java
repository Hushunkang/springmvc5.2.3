package com.atguigu.springmvc.handlers;

import com.atguigu.springmvc.domains.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author hskBeginner Email：2752962035@qq.com
 * @version 1.0
 * @description
 * @create 2020年03月25日 14时44分20秒
 */
@Controller
@RequestMapping("/requestMappingTest")
@SessionAttributes(value = {"user"}, types = {String.class})
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
     * 若类定义处未标注@RequestMapping注解，则方法处标记的URL相对于WEB应用的根目录
     * @return
     */
    @RequestMapping("/testRequestMapping")
    public String testRequestMapping() {
//        return RequestMappingTestHandler.SUCCESS;
        return SUCCESS;
    }

    /**
     * 使用method属性来指定请求的方式
     * 这里设置post类型，表明，我这个方法只接受post类型的请求，如果不是会响应405
     * HTTP Status 405 – 方法不允许
     * @return
     */
    @RequestMapping(value = "/testRequestMethod", method = RequestMethod.POST)
    public String testRequestMethod() {
        return SUCCESS;
    }

    /**
     * 细节：同一个业务逻辑控制器里面，可以设置两个方法的@RequestMapping注解的value属性值是一样滴
     * 原理：因为它们不仅仅可以通过请求的url来区分，还可以通过请求的方式等来区分
     * @return
     */
    @RequestMapping(value = "/testRequestMethod", method = RequestMethod.GET)
    public String testRequestMethod(Integer userId) {
        return SUCCESS;
    }

    /**
     * 了解一哈：可以使用params属性和headers属性来更加精确标识请求映射
     * params属性和headers属性还可以支持简单的表达式
     * @return
     */
    @RequestMapping(value = "/testParamsAndHeaders", params = {"userId", "age=18"}/*,headers={"Accept-Language=zh-CN,zh;q=0.9"}*/)
    public String testParamsAndHeaders() {
        return SUCCESS;
    }

    /**
     * 测试Ant风格的资源地址
     * @return
     */
    @RequestMapping(value = "/testAntResourceUrl/*/abc")
    public String testAntResourceUrl() {
        return SUCCESS;
    }

    /**
     * 测试@PathVariable注解：可以用来映射URL中的占位符到业务方法的参数中
     * 说明：spring mvc正是因为有此特性，才使得其支持rest风格的url
     * @return
     */
    @RequestMapping(value = "/testPathVariable/{userId}")
    public String testPathVariable(@PathVariable(value = "userId") Integer userId) {
        System.out.println("testPathVariable:" + userId);
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url get请求 表示查询
     * @return
     */
    @RequestMapping(value = "/testRest/{userId}", method = RequestMethod.GET)
    public String testRestGet(@PathVariable(value = "userId") Integer userId) {
        System.out.println("testRest get请求:" + userId);
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url post请求 表示新增
     * @return
     */
    @RequestMapping(value = "/testRest", method = RequestMethod.POST)
    public String testRestPost() {
        System.out.println("testRest post请求");
        return SUCCESS;
    }

    /**
     * 曲线救国：请求重定向，用于解决delete或者put请求405问题
     * @return
     */
    @RequestMapping(value = "/toSuccess")
    public String toSuccess() {
        return SUCCESS;
    }

    /**
     * 测试Rest风格的url delete请求 表示删除
     * @return
     */
    @RequestMapping(value = "/testRest/{userId}", method = RequestMethod.DELETE)
    public String testRestDelete(@PathVariable(value = "userId") Integer userId) {
        System.out.println("testRest delete请求:" + userId);
        return "redirect:/requestMappingTest/toSuccess";
    }

    /**
     * 测试Rest风格的url put请求 表示更新
     * @return
     */
    @RequestMapping(value = "/testRest/{userId}", method = RequestMethod.PUT)
    public String testRestPut(@PathVariable(value = "userId") Integer userId) {
        System.out.println("testRest put请求:" + userId);
        return "redirect:/requestMappingTest/toSuccess";
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
     *
     * 遇到的问题：https://blog.csdn.net/weixin_45165669/article/details/104617304
     */

    /**
     * @param userId
     * @param age
     * @return
     * @RequestParam注解用来映射请求参数 value值表示请求参数名
     * required表示该参数是必须的还是可选的，默认为true
     * defaultValue用来设置请求参数的默认值
     */
    @RequestMapping(value = "/testRequestParam")
    public String testRequestParam(@RequestParam(value = "userId", required = true) Integer userId,
                                   @RequestParam(value = "age", required = false, defaultValue = "0")/*Integer age*/int age) {
        System.out.println("testRequestParam: userId = " + userId + ",age = " + age);
        return SUCCESS;
    }

    /**
     * 了解一哈：映射请求头信息，用法同@RequestParam注解
     */
    @RequestMapping("/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value = "Accept-Language") String al) {
        System.out.println("testRequestHeader: Accept-Language:" + al);
        return SUCCESS;
    }

    /**
     * @param sessionId
     * @return
     * @CookieValue注解用来映射一个Cookie的值
     */
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String sessionId) {
        System.out.println("testCookieValue sessionId:" + sessionId);
        return SUCCESS;
    }

    /**
     * spring mvc会按请求参数名和pojo属性名进行自动匹配，自动为pojo填充属性值（底层原理使用反射调用属性的set方法），并且支持级联属性
     * 级联属性如：address.province、address.city等
     * @return
     */
    @RequestMapping(value = "/testPojo")
    public String testPojo(User user) {
        System.out.println("testPojo user:" + user);
        return SUCCESS;
    }

    /**
     * 可以使用Servlet原生的API作为目标方法的参数，具体支持以下类型
     * HttpServletRequest
     * HttpServletResponse
     * HttpSession
     * java.security.Principal
     * Locale InputStream
     * OutputStream
     * Reader
     * Writer
     * @param request
     * @param response
     * @param out
     * @return
     */
    @RequestMapping(value = "/testServletApi")
    public void testServletApi(HttpServletRequest request,
                               HttpServletResponse response,
                               Writer out) throws IOException {
        System.out.println("testServletApi request:" + request + ",response = " + response);
        out.write("Hello,world.");
//        return SUCCESS;
    }

    /**
     * 目标方法的返回值可以是ModelAndView类型
     * 其中可以包含视图信息和模型数据信息
     * 深入源码，浅出结论：spring mvc会把ModelAndView的model中数据放入到request域对象中
     * @return
     */
    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView() {
        ModelAndView modelAndView = new ModelAndView(SUCCESS);

        //添加模型数据到ModelAndView中
        modelAndView.addObject("datetime", new Date());

        return modelAndView;
    }

    /**
     * 目标方法可以添加Map类型（实际上也可以是Model类型或ModelMap类型）的参数
     * @param map
     * @return
     */
    @RequestMapping("/testMap")
    public String testMap(Map<String, Object> map) {
        System.out.println(map.getClass());//class org.springframework.validation.support.BindingAwareModelMap
        map.put("names", Arrays.asList("Tom", "Jack", "Rose"));
        return SUCCESS;
    }

    /**
     * @SessionAttributes 此注解除了可以通过属性名指定需要放到会话中的属性外（实际上使用的是value属性值）
     * 还可以通过模型属性的对象类型指定哪些模型属性需要放到会话中（实际上使用的是types属性值）
     * 注意：此注解只能放在类的上面，而不能修饰放方法
     */
    @RequestMapping("/testSessionAttributes")
    public String testSessionAttributes(Map<String, Object> map) {
        User user = new User("Tom", "123456", 18, "Tom@atguigu.com");
        map.put("user", user);
        map.put("school", "atguigu");
        return SUCCESS;
    }

    /**
     * 运行流程：
     * 1.执行@ModelAttribute注解修饰的方法：从数据库中取出对象，把对象放入到了Map中，key为user
     * 2.spring mvc从Map中取出User对象，并把表单的请求参数赋给与该User对象相对应的属性
     * 3.spring mvc把上述User对象传入目标方法的形参
     *
     * 注意：@ModelAttribute注解修饰的方法中，放入到Map时的key需要和目标方法形参类型的第一个字母变成小写的那个字符串一致
     *
     * spring mvc确定目标方法pojo类型形参的过程（待分析，后面有时间来Debug源码分析一波）
     *
     * 深入源码，浅出结论（待分析，后面有时间来Debug源码分析一波）
     *
     */
    @RequestMapping(value="/testModelAttribute")
    public String testModelAttribute(User user){
        System.out.println("修改：" + user);
        return  SUCCESS;
    }

    /**
     * 1.@ModelAttribute注解标记的方法，会在每个目标方法执行之前被spring mvc调用
     * 2.@ModelAttribute注解也可以来修饰目标方法pojo类型的形参，其value属性值有如下的作用：
     * 1).spring mvc会使用value属性值在implicitModel中查找对应的对象，若存在，则会直接传入到目标方法的形参中
     * 2).spring mvc会以value为key，pojo类型的对象为value，存入到request域对象中
     */
    @ModelAttribute
    public void getUser(@RequestParam(value="userId",required=false)Integer userId,
                        Map<String,Object> map){
        System.out.println("我是带有@ModelAttribute注解的方法...");
        //userId不为空表示更新操作为修改而不是新增
        if(userId != null){
            //模拟从数据库中获取对象
            User user = new User("Tom", "123456", 18, "Tom@atguigu.com");
            System.out.println("从数据库中获取一个对象：" + user);
            map.put("user", user);
        }
    }

    @RequestMapping("/testViewAndViewResolver")
    public String testViewAndViewResolver(){
        System.out.println("testViewAndViewResolver...");
        return SUCCESS;
    }

    /**
     * 自定义视图
     * @return
     */
    @RequestMapping("/testView")
    public String testView(){
        System.out.println("testView...");
        return "myView";
    }

    /**
     * 请求转发
     * @return
     */
    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("testForward");
        return "forward:/index.jsp";
    }

    /**
     * 请求重定向
     * @return
     */
    @RequestMapping("/testRedirect")
    public String testRedirect(){
        System.out.println("testRedirect");
        return "redirect:/index.jsp";
    }

}
