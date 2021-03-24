package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.utils.JSTools;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lpc
 * @create 2020-07-29-9:06
 * 在类前配置 path 可以定义前缀路径
 */
@SuppressWarnings("all")
@Controller
public class IndexController {

    /**
     * 转发首页和根页面
     */
    /*  method限定请求的方法   */
    @RequestMapping(path = "/{module}", method = RequestMethod.GET)
    public String forward(@PathVariable String module) {
        return module;
    }


    /**
     * 转发Admin下的内容
     */
    @RequestMapping(path = "admin/{module}")
    public String adminForward(@PathVariable String module,
                               HttpServletRequest request,
                               HttpServletResponse response) {

        /* 获取登录的用户 */
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        /* 判断登录权限 */
        if (user == null) {
            response.setContentType("text/html; charset=utf-8");
            try {
                response.getWriter().write(JSTools.alterUrl("请先登录", "/WTUShop/login"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /* 转发页面 */
        switch (module) {
            case "manage":
                return "admin/manage";
            case "navigation":
                return "admin/navigation";
            default:
                return "admin/" + module + "/index";
        }
    }


    /**
     * 转发User目录下
     */
    @RequestMapping(path = "user/{module}")
    public String userForward(@PathVariable String model) {
        return "user/" + model + "/index";
    }

    /**
     * 转发其他页面
     */
    @RequestMapping(path = "{module}/{page}")
    public String pageForward(@PathVariable String module, @PathVariable String page) {
        return "home/" + module + "/" + page;
    }


}
