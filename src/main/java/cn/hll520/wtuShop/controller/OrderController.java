package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.OrderService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
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
 * @create 2020-08-05-10:54
 */
@Controller
@RequestMapping("order/")
public class OrderController {

    @Autowired
    private OrderService service;

    @ResponseBody
    @RequestMapping("info/getAll")
    public JsonResult getAll(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult result = new JsonResult();
        PageInfo<Order> data = service.getAll(pageIndex, pageSize);
        if (data.getList().size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(data);
        return result;
    }

    @RequestMapping("info/{orderKey}")
    public String viewInfo(@PathVariable String orderKey) {
        return "kill/order/pay";
    }

    @ResponseBody
    @RequestMapping("info/{orderKey}/getInfo")
    public JsonResult getInfo(@PathVariable String orderKey) {
        JsonResult result = new JsonResult();
        List<Order> orderList = service.getOrderByKey(orderKey);
        if (orderList.size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(orderList);
        return result;
    }

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
