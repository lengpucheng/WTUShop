package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.service.KillGoodsService;
import cn.hll520.wtuShop.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-8:51
 */
@Controller
@RequestMapping(path = "kill/order/")
public class KillOrderController {

    @Autowired
    private KillGoodsService service;

    /* 订单详情 */
    @RequestMapping(path = "pay/{killOrderId}")
    public String viewOrder(@PathVariable Integer killOrderId) {
        return "kill/order/pay";
    }

    @ResponseBody
    @RequestMapping(path = "pay/{killOrderId}/getInfo")
    public JsonResult getOrderInfo(@PathVariable Integer killOrderId) {
        JsonResult result = new JsonResult();
        List<Order> order = service.killOrderInfo(killOrderId);
        if (order == null||order.size()<1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(order);
        return result;
    }
}
