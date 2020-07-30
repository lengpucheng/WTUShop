package cn.hll520.wtuShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author lpc
 * @create 2020-07-29-9:06
 * 在类前配置 path 可以定义前缀路径
 */
@Controller
public class IndexController {

    /*  method限定请求的方法   */
    @RequestMapping(path = "/index")
    public String index(){
        return "index";
    }
}
