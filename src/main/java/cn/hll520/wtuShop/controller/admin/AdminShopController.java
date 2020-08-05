package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Shop;
import cn.hll520.wtuShop.service.ShopService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lpc
 * @create 2020-08-05-21:14
 */
@Controller
@RequestMapping("admin/shopping/")
public class AdminShopController {
    @Autowired
    private ShopService service;

    @ResponseBody
    @RequestMapping("showAll")
    public JsonResult showAll(@RequestParam(defaultValue = "1") Integer pageIndex,
                              @RequestParam(defaultValue = "5") Integer pageSize){
        JsonResult result=new JsonResult();
        PageInfo<Shop> pageInfo = service.showAdmin(pageIndex,pageSize);
        if(pageInfo.getList().size()<1)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(pageInfo);
        return result;
    }
}
