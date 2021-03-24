package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.AdminService;
import cn.hll520.wtuShop.service.UserInfoService;
import cn.hll520.wtuShop.utils.JSTools;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @Autowired
    private UserInfoService infoService;

    @RequestMapping("login")
    public String login(HttpServletRequest request,
                        HttpServletResponse response,
                        Admin admin) {
        Admin login = service.login(admin);

        response.setContentType("text/html; charset=utf-8");
        if (login != null) {
            HttpSession session = request.getSession();
            session.setAttribute("admin", login);
            session.setAttribute("user", login.getUserInfo());
            return "redirect:/admin/manage";
        } else {
            response.setContentType("text/html; charset=utf-8");
            try {
                response.getWriter().write(JSTools.alterBack("登陆失败！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 获取当前的管理员信息
     */
    @ResponseBody
    @RequestMapping(path = "getInfo")
    public JsonResult getUserInfo(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        System.out.println("_____当前登录：" + admin);
        if (admin == null || admin.getUsername() == null) {
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setMsg("请重新登录");
        } else
            admin.setPassword("*******");
        result.setData(admin);
        return result;
    }

    /**
     * 退出登录
     */
    @RequestMapping(path = "signOut")
    public String signOut(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        session.removeAttribute("admin");
        session.invalidate();
        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().write(JSTools.alterReplace("已安全退出", "/WTUShop/admin/manage"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 显示全部用户
     */
    @ResponseBody
    @RequestMapping(path = "getUserAll")
    public JsonResult getAllUser(
            @RequestParam(defaultValue = "1") Integer pageIndex,
            @RequestParam(defaultValue = "5") Integer pageSize
            , HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("请登录！");
            return result;
        }
        PageInfo<UserInfo> all = infoService.getAll(pageIndex, pageSize);
        if (all.getList().size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(all);
        return result;
    }

    /**
     * 删除用户
     */
    @RequestMapping("del/{id}")
    public String delUser(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            try {
                response.getWriter().write(JSTools.alterUrl("权限不足","/WTUShop/admin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "admin";
        }
        int del = infoService.del(id);
        if (del < 1) {
            try {
                response.getWriter().write(JSTools.alterBack("删除失败"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                response.getWriter().write(JSTools.alterBack("删除成功！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    /**
     * 转发
     */
    @RequestMapping("edit/{id}")
    public String edit(@PathVariable Integer id, HttpServletRequest request) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            return "admin";
        }
        return "admin/userAdmin/edit";
    }

    /**
     * 获取
     */
    @ResponseBody
    @RequestMapping("edit/{id}/getData")
    public JsonResult getData(@PathVariable Integer id, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("请登录！");
            return result;
        }
        UserInfo userById = infoService.getUserById(id);
        if (userById == null)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(userById);
        return result;
    }

    /** 修改 */
    @RequestMapping("edit/{id}/doEdit")
    public String doEdit(@PathVariable Integer id,UserInfo userInfo,HttpServletRequest request,HttpServletResponse response){
        response.setContentType("text/html; charset=utf-8");
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if (admin == null) {
            try {
                response.getWriter().write(JSTools.alterUrl("权限不足","/WTUShop/admin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        userInfo.setUserid(id);
        int edit = infoService.edit(userInfo);
        if(edit<1){
            try {
                response.getWriter().write(JSTools.alterBack("修改失败"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            try {
                response.getWriter().write(JSTools.alterUrl("修改成功","/WTUShop/admin/userAdmin"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        return null;


    }

}
