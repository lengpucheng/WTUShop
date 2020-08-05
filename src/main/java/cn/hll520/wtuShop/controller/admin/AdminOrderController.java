package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.service.OrderService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-10:54
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("admin/order/")
public class AdminOrderController {

    @Autowired
    private OrderService service;

    /** 获取全部信息(可以查看任意用户) */
    @ResponseBody
    @RequestMapping("getAll")
    public JsonResult getAll(@RequestParam(defaultValue = "1") Integer pageIndex,
                             @RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult result = new JsonResult();
        PageInfo<Order> data = service.getAll(pageIndex, pageSize);
        if (data.getList().size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(data);
        return result;
    }

    /** 转发订单详情页(可以查看任意用户)  */
    @RequestMapping("info/{orderKey}")
    public String viewInfo(@PathVariable String orderKey) {
        return "user/order/info";
    }


    /** 获取订单详情信息(可以查看任意用户) */
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

    /** 删除订单 */
    @ResponseBody
    @RequestMapping("doDel/{orderKey}")
    public JsonResult del(@PathVariable String orderKey) {
        JsonResult result = new JsonResult();
        int rows= service.delOrder(orderKey);
        if (rows<1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(rows);
        return result;
    }

}
