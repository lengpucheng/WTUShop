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
    public String forward(@PathVariable String module) { return module; }

    /** 转发后台 */
    @RequestMapping(path = "/manage")
    public String manageForward(HttpServletRequest request, HttpServletResponse response){
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");

        if(user==null){
            response.setContentType("text/html; charset=utf-8");
            try {
                response.getWriter().write(JSTools.alterUrl("请先登录","login"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        return "manage";
    }

    /**
     * 转发admin下的模块首页
     */
    @RequestMapping(path = "/admin/{module}")
    public String appAdminForward(@PathVariable String module) { return "admin/" + module + "/index"; }


}
