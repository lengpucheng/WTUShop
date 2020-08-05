package cn.hll520.wtuShop.controller.user;

import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.OrderService;
import cn.hll520.wtuShop.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static cn.hll520.wtuShop.service.OrderService.*;

/**
 * @author lpc
 * @create 2020-08-05-13:00
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("order/")
public class OrderController {

    @Autowired
    private OrderService service;

    /**
     * 获取当前用户全部订单
     */
    @ResponseBody
    @RequestMapping("info/getAll")
    public JsonResult getAll(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "5") Integer pageSize,
                             HttpServletRequest request) {

        JsonResult result = new JsonResult();

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");

        List<List<Order>> data = service.getAllByUserID(user.getUserid(), pageIndex, pageSize);
        if (data.size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(data);
        return result;
    }

    /**
     * 转发订单详情页
     */
    @RequestMapping("info/{orderKey}")
    public String viewInfo(@PathVariable String orderKey) {
        return "info";
    }


    /**
     * 获取订单详情信息,需要验证用户
     */
    @ResponseBody
    @RequestMapping("info/{orderKey}/getInfo")
    public JsonResult getInfo(@PathVariable String orderKey, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");

        List<Order> orderList = service.getOrderByKey(orderKey);
        if (orderList.size() >= 1) {
            if (orderList.get(0).getUserId() == user.getUserid()) {
                result.setData(orderList);
                return result;
            } else
                result.setMsg("非法的请求！");
        } else
            result.setMsg("没有找到该订单！");
        result.setStatusCode(JsonResult.STATUS_ERROR);
        return result;
    }

    /**
     * 支付订单
     */
    @ResponseBody
    @RequestMapping("{orderKey}/pay")
    public JsonResult pay(@PathVariable String orderKey,
                          @RequestParam(defaultValue = "9999999999") Integer payPrice,
                          HttpServletRequest request) {

        JsonResult result = new JsonResult();
        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        int payStatus = service.payOrder(orderKey, user, payPrice);
        switch (payStatus) {
            case PAY_SUCCESS:
                result.setMsg("支付成功！");
                break;
            case NOT_LOGIN:
                result.setStatusCode(JsonResult.STATUS_ERROR);
                result.setMsg("请先登录！");
                break;
            case NOT_PAY_PRICE:
                result.setStatusCode(JsonResult.STATUS_ERROR);
                result.setMsg("金额不足！");
                break;
            case PAY_FAIL:
            default:
                result.setStatusCode(JsonResult.STATUS_ERROR);
                result.setMsg("支付失败！");
                break;
        }
        result.setData(payStatus);
        return result;
    }

}
