package cn.hll520.wtuShop.controller;

import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.service.GoodsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-29-9:15
 */

@Controller
/* 限定路径前缀 */
@RequestMapping(path = "admin/goods/")
public class GoodsController {
    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }

    @RequestMapping(path = "getAll")
    /* 该注解可以将List转换为Json */
    @ResponseBody
    public List<Goods> getGoods() {

        return service.getAll();
    }

}
