package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.UserInfoService;
import cn.hll520.wtuShop.utils.JSTools;
import cn.hll520.wtuShop.utils.JsonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author lpc
 * @create 2020-07-28-16:05
 * <p>
 * MVC三层结构  (M->C->V)
 * 一、中心总控制器(DispatcherServlet)，收到请求后：将请求转发到HandlerMapping (方法映射/方法索引)
 * ————1.M     HandlerMapping(方法映射/索引)
 * ————I.  根据请求的地址遍历注解  找到controller路径，返回给总控制器
 * ————II. 总控制器根据路径，将请求参数转发给对应的HandlerAdapter(内容提供者)中对应的controller(控制器）
 * <p>
 * ————2.C     Controller(控制器)、HandlerAdapter(内容提供者)
 * ————I.  处理请求和数据，并将处理好的请求和数据封装到 ModelAndView中(模型和视图,相当于安卓的 ViewModel)
 * ————II. 将封装好的ModelAndView(视图和内容),返回给HandlerAdapter(内容提供者)
 * ————III.HandlerAdapter(内容提供者)把处理好的数据返回给总控制器，并由总控制器传递给ViewResolver(视图解析)
 * <p>
 * ————3.V      ViewResolver(视图解析者)、View(视图层)
 * ————I.  解析ModelAndView中的数据和视图信息
 * ————II. 将解析好的信息返回给总控制器
 * ————III.调用View渲染JSP视图
 * <p>
 * 二、将视图和数据响应给请求的用户
 * <p>
 * <p>
 * 方法说明：
 * —————— 构造器
 * 1. 定义公用构造器，参数为定义过Spring注解的对象
 * 2. spring框架会在启动控制器时，将对应对象生成并注入到构造器
 * 3. 通过构造器将地址赋值给当前类中的对象
 * ————— @Autowired
 * 1. 可以替代构造器方法
 * 2. 直接在声明的对象上使用注解，控制器被创建时会自动注入
 * 3. 此方法不推荐使用
 * <p>
 * <p>
 * —————— ModelAndView  模型和视图
 * ——————1.setViewName    设置对应视图的名称，与视图臣文件名一一对应
 * ————I.  不需要后缀，直接写名称是转发
 * ————II. 加上前缀 redirect:  为302重定向
 * ——————2.addObject(key,obj) 像解析器中传入以键值对方式的对象
 * <p>
 * —————— ModelAndView 自动拆装
 * ——————1. 将返回对象设置为 String，视图名，直接返回为字符串
 * ——————2. 添加数据等直接使用 request 的域对象即可
 * ————I.  addObject(key,obj)  对应 request.setAttribute(key,obj)
 */
@Controller
@RequestMapping(path = "user/")
public class UserInfoController {

    /*
     * 根据注解 自动找该类型的实现类 由Spring自动创建
     * */
    private final UserInfoService service;

    /**
     * 通过构造函数获取当前业务对象
     */
    public UserInfoController(UserInfoService service) {
        this.service = service;
    }

    /**
     * 执行登录
     */
    @RequestMapping(path = "dologin")
    public String login2(HttpServletRequest request,
                         HttpServletResponse response,
                         UserInfo userInfo) {
        UserInfo loginUser = service.login(userInfo);
        if (loginUser != null) {
            request.getSession().setAttribute("user", loginUser);
            /* 不写 redirect: 表示请求转发   带上表示302重定向*/
            return "redirect:/goods/search";

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
     * 执行注册
     */
    @RequestMapping(path = "doRegister")
    public String doRegister(UserInfo userInfo, HttpServletResponse response) {

        response.setContentType("text/html; charset=utf-8");
        if(userInfo.getUsername()==null||userInfo.getUsername().isEmpty()||userInfo.getPassword()==null||userInfo.getPassword().isEmpty()){
            try {
                response.getWriter().write(JSTools.alterBack("用户名或密码不能为空！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        int register = service.register(userInfo);

        System.out.println(register);

        if (register==0) {
            try {
                response.getWriter().write(JSTools.alterBack("注册失败！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else if(register==-1){
            try {
                response.getWriter().write(JSTools.alterBack("注册失败！用户名重复！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }else {
            try {
                response.getWriter().write(JSTools.alterUrl("注册成功！","http://127.0.0.1/WTUShop/login"));
            } catch (IOException e) {
                e.printStackTrace();
            }        }
        return null;
    }

    /** 获取当前的用户登录信息 */
    @ResponseBody
    @RequestMapping(path = "getInfo")
    public JsonResult getUserInfo(HttpServletRequest request){
        JsonResult result=new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        System.out.println("_____当前登录："+user);
        if(user==null||user.getUsername()==null){
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setMsg("请重新登录");
        }else
            user.setPassword("*******");
        result.setData(user);
        return result;
    }

    /** 退出登录 */
    @RequestMapping(path = "signOut")
    public String signOut(HttpServletRequest request,HttpServletResponse response){
        request.getSession().removeAttribute("user");
        request.getSession().invalidate();
        response.setContentType("text/html; charset=utf-8");
        try {
            response.getWriter().write(JSTools.alterReplace("已安全退出","http://127.0.0.1/WTUShop/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
