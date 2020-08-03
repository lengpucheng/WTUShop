package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.KillGoods;
import cn.hll520.wtuShop.service.KillGoodsService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Objects;

/**
 * @author lpc
 * @create 2020-08-03-9:49
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(path = "kill/goods/")
public class KillGoodsController {

    @Autowired
    private KillGoodsService service;

    /**
     * 转发kill/goods下的页面
     */
    @RequestMapping("{method}")
    public String appForward(@PathVariable String method) {
        return "kill/goods/" + method;
    }


    /**
     * 获取所有
     */
    @ResponseBody
    @RequestMapping(path = "getAll")
    public JsonResult getAll(@RequestParam(defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult result = new JsonResult();

        PageInfo<KillGoods> all = service.getAll(pageIndex, pageSize);
        if (all.getList().size() < 1)
            result.setStatusCode(JsonResult.STATUS_ERROR);
        result.setData(all);
        return result;
    }

    /**
     * 添加
     */
    @ResponseBody
    @RequestMapping(path = "doAdd")
    public JsonResult doAdd(KillGoods goods) {
        JsonResult result = new JsonResult();
        int rows = service.addKillGoods(goods);
        if (rows != 1) {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("错误");
        }
        result.setData(rows);
        return result;
    }

    /**
     * 页面索引  required 是否是必须的？
     */
    @RequestMapping("{method}/{id}")
    public String getByID(@PathVariable Integer id, @PathVariable String method) {
        System.out.println("id---:" + id);
        System.out.println(method);

        return "kill/goods/" + method;
    }


    /**
     * 获取当前ID的信息
     */
    @ResponseBody
    @RequestMapping({"info/{id}/getGoods", "edit/{id}/getGoods"})
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

        if(killGoods.getDateStart()!=null&&killGoods.getDateEnd()!=null) {
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
     * 编辑
     */
    @ResponseBody
    @RequestMapping("edit/{id}/doEdit")
    public JsonResult doEdit(@PathVariable Integer id, KillGoods goods) {

        System.out.println(goods);

        JsonResult result = new JsonResult();
        if (!Objects.equals(goods.getKillId(), id)) {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("修改参数不一致！");
            return result;
        }
        int rows = service.editKillGoodsByID(goods);
        if (rows != 1) {
            result.setStatusCode(JsonResult.STATUS_ERROR);
        }
        result.setData(rows);
        return result;
    }


}
