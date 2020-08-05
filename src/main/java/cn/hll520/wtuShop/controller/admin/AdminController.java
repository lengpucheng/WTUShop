package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.service.AdminService;
import cn.hll520.wtuShop.utils.JSTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author lpc
 * @create 2020-07-31-17:31
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("sa/")
public class AdminController {

    @Autowired
    private AdminService service;

    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        Admin admin){
        Admin login = service.login(admin);

        response.setContentType("text/html; charset=utf-8");
        if(login!=null){
            HttpSession session = request.getSession();
            session.setAttribute("admin", login);
            session.setAttribute("user", login.getUserInfo());
            return "redirect:/admin/manage";
        }else {
            response.setContentType("text/html; charset=utf-8");
            try {
                response.getWriter().write(JSTools.alterBack("登陆失败！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }


}
