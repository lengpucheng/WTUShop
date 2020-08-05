package cn.hll520.wtuShop.controller.user;

import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.service.GoodsService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lpc
 * @create 2020-08-05-17:52
 */
@Controller
@RequestMapping("goods/")
public class UserGoodsController {

    @Autowired
    private GoodsService service;

    /** 搜索页面转发 */
    @RequestMapping("search/{key}")
    public String searchForward(@PathVariable(required = false) String key){
        System.out.println("正在搜索:___"+key);
        return "home/goods/search";

    }

    /** 搜索获取数据 */
    @ResponseBody
    @RequestMapping({"search/{key}/getData", "search/getData"})
    public JsonResult getData(@PathVariable(required = false) String key,
                              @RequestParam(defaultValue = "1") Integer pageIndex,
                              @RequestParam(defaultValue = "6") Integer pageSize) {
        JsonResult result = new JsonResult();
        Goods goods = new Goods();
        key = key == null ? "" : key;
        goods.setName(key);
        PageInfo<Goods> pageInfo = service.searchGoods(goods, pageIndex, pageSize);
        if (pageInfo.getList().size() < 1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(pageInfo);
        return result;
    }

}
