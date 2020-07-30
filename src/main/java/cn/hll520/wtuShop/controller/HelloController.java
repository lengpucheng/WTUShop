package cn.hll520.wtuShop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lpc
 * @create 2020-07-28-9:07
 */
@Controller
public class HelloController {

    @RequestMapping(path = "/hello")
    public String hello(HttpServletRequest request) {
        request.setAttribute("msg","hello spring mvc");
        return "hello";
    }
}
