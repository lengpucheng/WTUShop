package cn.hll520.wtuShop.controller.user;

import cn.hll520.wtuShop.pojo.Shop;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.ShopService;
import cn.hll520.wtuShop.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static cn.hll520.wtuShop.service.ShopService.*;

/**
 * @author lpc
 * @create 2020-08-05-21:14
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("shopping/")
public class ShopController {
    @Autowired
    private ShopService service;

    /**
     * 转发购物车
     */
    @RequestMapping("list")
    public String shopForward() {
        return "user/shopping/list";
    }

    /**
     * 获取全部商品
     */
    @ResponseBody
    @RequestMapping("list/getData")
    public JsonResult getData(HttpServletRequest request) {
        JsonResult result = new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || user.getUserid() == null) {
            result.setStatusCode(JsonResult.STATUS_SUCCESS_REDIRECT);
            result.setMsg("请登录！");
            result.setData("http://127.0.0.1/WTUShop/login");
        } else {
            List<Shop> show = service.show(user.getUserid());
            if (show.size() < 1)
                result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setData(show);
        }
        return result;
    }

    /**
     * 添加商品到购物车
     */
    @ResponseBody
    @RequestMapping("addShop")
    public JsonResult addShop(Integer goodsId, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        if(goodsId==null){
            result.setStatusCode(JsonResult.STATUS_ERROR);
            return result;
        }

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || user.getUserid() == null) {
            result.setStatusCode(JsonResult.STATUS_SUCCESS_REDIRECT);
            result.setMsg("请登录！");
            result.setData("http://127.0.0.1/WTUShop/login");
        } else {
            int add = service.add(user.getUserid(), goodsId);
            if (add < 1)
                result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setData(add);
        }
        return result;
    }

    /** 移除购物车的商品 */
    @ResponseBody
    @RequestMapping("delShop")
    public JsonResult delShop(@RequestParam(defaultValue = "-1") Integer shopID,
                              HttpServletRequest request){


        System.out.println("___del:goodsId___"+shopID);

        JsonResult result = new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || user.getUserid() == null) {
            result.setStatusCode(JsonResult.STATUS_SUCCESS_REDIRECT);
            result.setMsg("请登录！");
            result.setData("http://127.0.0.1/WTUShop/login");
        } else {
            int del = service.del(user.getUserid(), shopID);
            switch (del){
                case 1:
                    result.setData(del);
                    break;
                case -1:
                    result.setStatusCode(JsonResult.STATUS_ERROR);
                    result.setMsg("权限不足！");
                    break;
                case 0:
                default:
                    result.setStatusCode(JsonResult.STATUS_NOTFOUND);
                    break;
            }
        }
        return result;
    }

    /** 修改数量 */
    @ResponseBody
    @RequestMapping("edit")
    public JsonResult editSUM(Integer shopId,Integer number,HttpServletRequest request){
        JsonResult result = new JsonResult();
        if(number==null||number<1){
            result.setStatusCode(JsonResult.STATUS_ERROR);
            return result;
        }
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || user.getUserid() == null) {
            result.setStatusCode(JsonResult.STATUS_SUCCESS_REDIRECT);
            result.setMsg("请登录！");
            result.setData("http://127.0.0.1/WTUShop/login");
        } else {
            int edit = service.edit(user.getUserid(), shopId, number);
            switch (edit){
                case 1:
                    result.setData(edit);
                    break;
                case -1:
                    result.setStatusCode(JsonResult.STATUS_ERROR);
                    result.setMsg("权限不足！");
                    break;
                case 0:
                default:
                    result.setStatusCode(JsonResult.STATUS_NOTFOUND);
                    break;
            }
        }
        return result;
    }

    /** 提交订单 */
    @ResponseBody
    @RequestMapping("submitShop")
    public JsonResult submitShop(HttpServletRequest request){
        JsonResult result = new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if (user == null || user.getUserid() == null) {
            result.setStatusCode(JsonResult.STATUS_SUCCESS_REDIRECT);
            result.setMsg("请登录！");
            result.setData("http://127.0.0.1/WTUShop/login");
        } else {
            String orderKey = service.makeOrder(user.getUserid());
            switch (orderKey){
                case NOT_FOUND:
                    result.setStatusCode(JsonResult.STATUS_NOTFOUND);
                    result.setMsg("购物车内容为空");
                    break;
                case SUBMIT_FAILED:
                    result.setStatusCode(JsonResult.STATUS_NOTFOUND);
                    result.setMsg("提交失败！");
                    break;
                case ERROR:
                    Shop error = service.getError();
                    result.setStatusCode(JsonResult.STATUS_ERROR);
                    result.setMsg(error.getGoods().getName());
                    result.setData(error);
                    break;
                default:
                    result.setData(orderKey);
                    break;
            }
        }
        return result;
    }


}
