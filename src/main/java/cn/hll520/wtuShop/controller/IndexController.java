package cn.hll520.wtuShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author lpc
 * @create 2020-07-29-9:06
 * 在类前配置 path 可以定义前缀路径
 */
@SuppressWarnings("all")
@Controller
public class IndexController {

    /**
     * 首页
     */
    /*  method限定请求的方法   */
    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String forward() { return "index"; }


    /**
     * 转发admin下的模块首页
     */
    @RequestMapping(path = "/admin/{module}")
    public String appAdminForward(@PathVariable String module) { return "admin/" + module + "/index"; }


}
