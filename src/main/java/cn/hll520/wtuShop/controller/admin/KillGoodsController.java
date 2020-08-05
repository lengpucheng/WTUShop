package cn.hll520.wtuShop.controller.admin;

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

import java.util.Objects;

/**
 * @author lpc
 * @create 2020-08-03-9:49
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(path = "admin/kill/")
public class KillGoodsController {

    @Autowired
    private KillGoodsService service;

    /**
     * 转发kill/goods下的页面
     */
    @RequestMapping("{method}")
    public String appForward(@PathVariable String method) {
        return "admin/kill/" + method;
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
     * 转发编辑页面
     */
    @RequestMapping("edit/{id}")
    public String getByID(@PathVariable Integer id) {
        System.out.println("id---:" + id);
        return "admin/kill/edit";
    }

    /** 获取编辑信息 */
    @ResponseBody
    @RequestMapping("edit/{id}/getGoods")
    public JsonResult getGoodsInfo(@PathVariable Integer id) {
        JsonResult result = new JsonResult();
        KillGoods killGoods = service.getKillGoodsByID(id);
        result.setData(killGoods);
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
