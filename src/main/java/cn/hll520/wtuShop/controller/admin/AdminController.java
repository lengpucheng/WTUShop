package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.service.AdminService;
import cn.hll520.wtuShop.utils.JSTools;
import cn.hll520.wtuShop.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    /** 获取当前的管理员信息 */
    @ResponseBody
    @RequestMapping(path = "getInfo")
    public JsonResult getUserInfo(HttpServletRequest request){
        JsonResult result=new JsonResult();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("_____当前登录："+admin);
        if(admin==null||admin.getUsername()==null){
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setMsg("请重新登录");
        }else
            admin.setPassword("*******");
        result.setData(admin);
        return result;
    }

    /** 退出登录 */
    @RequestMapping(path = "signOut")
    public String signOut(HttpServletRequest request,HttpServletResponse response){
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("admin");
        session.invalidate();
        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().write(JSTools.alterReplace("已安全退出","http://127.0.0.1/WTUShop/admin/manage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
