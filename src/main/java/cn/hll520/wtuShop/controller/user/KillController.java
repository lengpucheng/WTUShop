package cn.hll520.wtuShop.controller.user;

import cn.hll520.wtuShop.pojo.KillGoods;
import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.KillGoodsService;
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
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import static cn.hll520.wtuShop.service.KillGoodsService.*;

/**
 * @author lpc
 * @create 2020-08-05-12:48
 */
@SuppressWarnings("all")
@Controller
@RequestMapping("kill/")
public class KillController {

    @Autowired
    private KillGoodsService service;


    /** 转发秒杀列表 */
    @RequestMapping("list")
    public String killForWard(){
        return "home/kill/index";
    }

    /** 转发全部秒杀 */
    @ResponseBody
    @RequestMapping("list/getData")
    public JsonResult getList(@RequestParam(defaultValue ="1") Integer pageIndex,
                              @RequestParam(defaultValue = "5") Integer pageSize){
        JsonResult result=new JsonResult();
        PageInfo<KillGoods> all = service.getAll(pageIndex, pageSize);
        if(all.getList().size()<1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(all);
        return result;
    }

    /*
     *
     *     秒杀订单
     *
     * */

    /**
     * 转发秒杀订单详情
     */
    @RequestMapping(path = "pay/{killOrderId}")
    public String viewOrder(@PathVariable Integer killOrderId) {
        return "user/order/info";
    }

    /**
     * 获取秒杀订单详情
     */
    @ResponseBody
    @RequestMapping(path = "pay/{killOrderId}/getInfo")
    public JsonResult getOrderInfo(@PathVariable Integer killOrderId) {
        JsonResult result = new JsonResult();
        List<Order> order = service.killOrderInfo(killOrderId);
        if (order == null || order.size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(order);
        return result;
    }




    /*
     *
     *    秒杀活动详情
     *
     * */


    /**
     * 转发秒杀活动详情页面
     */
    @RequestMapping("info/{id}")
    public String getInfo(@PathVariable Integer id) {
        System.out.println("id---:" + id);
        return "home/goods/infoKill";
    }

    /**
     * 获取当前秒杀活动详情
     */
    @ResponseBody
    @RequestMapping("info/{id}/getGoods")
    public JsonResult getGoods(@PathVariable Integer id) {
        JsonResult result = new JsonResult();
        KillGoods killGoods = service.getKillGoodsByID(id);
        if (killGoods == null) {
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            return result;
        }

        /* 剩余时间 */
        long remainSeconds = 0;
        /* 表示秒杀状态  0 未开始  1正在进行中 2结束 */
        int killStatus = 0;
        /* 距离1970年1月1 过的毫秒 */

        if (killGoods.getDateStart() != null && killGoods.getDateEnd() != null) {
            long min = killGoods.getDateStart().getTime();
            long max = killGoods.getDateEnd().getTime();
            /* 现在距离1970 1 1 过的毫秒 */
            long now = System.currentTimeMillis();
            remainSeconds = max - min;

            if (now < min) {
                remainSeconds = (min - now) / 1000;
            } else if (now <= max) {
                killStatus = 1;
                remainSeconds = (max - now) / 1000;
            } else {
                killStatus = 2;
            }
        }

        HashMap<String, Object> map = new HashMap<>();
        map.put("remainSeconds", remainSeconds);
        map.put("killStatus", killStatus);
        map.put("killGoods", killGoods);

        result.setData(map);
        return result;
    }


    /**
     * 立即秒杀
     */
    @RequestMapping(path = "info/{id}/kill")
    public String kill(@PathVariable Integer id, HttpServletRequest request, HttpServletResponse response) {

        UserInfo user = (UserInfo) request.getSession().getAttribute("user");
        if(user==null){
            try {
                response.getWriter().write(JSTools.alterUrl("请先登录","/login"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        System.out.println(user);
        System.out.println(id);
        // X用户 秒杀了 X商品
        int kill = service.kill(id, user);
        System.out.println(kill);
        try {
            response.setContentType("text/html; charset=utf-8");
            switch (kill) {
                case NOT_KILL_TIME:
                    response.getWriter().write(JSTools.alterBack("不在秒杀时间段"));
                    break;
                case NOT_ENOUGH_NUMBER:
                    response.getWriter().write(JSTools.alterBack("库存不足，商品已经被抢购一空"));
                    break;
                case NOT_JUST_ONCE:
                    response.getWriter().write(JSTools.alterBack("一种商品只能购买一次哦！"));
                    break;
                case KILL_FAIL:
                    response.getWriter().write(JSTools.alterBack("抢购失败！"));
                    break;
                case KILL_SUCCESS:
                default:
                    /* 抢购成功就跳转支付 */
                    response.getWriter().write(JSTools.alterUrl(
                            "抢购成功", "/kill/pay/" + kill));
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
