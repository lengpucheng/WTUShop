package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Shop;
import cn.hll520.wtuShop.service.ShopService;
import cn.hll520.wtuShop.utils.JSTools;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @RequestMapping("del/{id}")
    public String del(@PathVariable Integer id, HttpServletResponse response){
        int i = service.delAdmin(id);
        response.setContentType("text/html; charset=utf-8");
        if(i<1) {
            try {
                response.getWriter().write(JSTools.alterBack("删除失败！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            try {
                response.getWriter().write(JSTools.alterBack("删除成功！"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

}
