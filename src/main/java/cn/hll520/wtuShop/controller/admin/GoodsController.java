package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.service.GoodsService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author lpc
 * @create 2020-07-29-9:15
 */
@SuppressWarnings("all")
@Controller
/* 限定路径前缀 */
@RequestMapping(path = "admin/goods/")
public class GoodsController {
    private final GoodsService service;

    public GoodsController(GoodsService service) {
        this.service = service;
    }


    /** 页面转发 */
    @RequestMapping(path = "{method}")
    public String appForward(@PathVariable String method){
        return "admin/goods/"+method;
    }


    /** 获取全部  */
    @RequestMapping(path = "getAll")
    /* 该注解可以将List转换为Json */
    @ResponseBody
    public List<Goods> getGoods() {

        return service.getAll();
    }

    /** 模糊搜索 */
    @ResponseBody
    @RequestMapping(path = "search")
    public JsonResult search(Goods goods, @RequestParam(defaultValue = "1") Integer pageIndex,@RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult result = new JsonResult();
        PageInfo<Goods> pageInfo = service.searchGoods(goods, pageIndex, pageSize);
        if (pageInfo.getList().size() < 1) {
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
            result.setMsg("没有找到相关内容");
        }
        result.setData(pageInfo);
        return result;
    }


    /** 添加 */
    @ResponseBody
    @RequestMapping(path = "doAdd")
    public JsonResult doAdd(Goods goods){
        JsonResult result=new JsonResult();
        goods.setCreateTime(new Date());
        int rows = service.addGoods(goods);
        if(rows==0){
            result.setStatusCode(JsonResult.STATUS_ERROR);
        }else if(rows==-1){
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("类别设置错误！");
        }
        result.setData(rows);

        return result;
    }

    /** 删除 */
    @ResponseBody
    @RequestMapping(path = "doDel/{id}")
    public JsonResult doDel(@PathVariable Integer id){
        JsonResult result=new JsonResult();
        int rows=service.delGoodsByID(id);

        if(rows==0){
            result.setStatusCode(JsonResult.STATUS_ERROR);
        }else if(rows==-1){
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("类别更新错误！");
        }
        result.setData(rows);
        return result;
    }

    /** 编辑页面 */
    @RequestMapping(path = "edit/{id}")
    public String edit(@PathVariable Integer id, HttpServletRequest request){
        Goods goods = service.getGoodsByID(id);
        request.getSession().setAttribute("goods",goods);
        return "admin/goods/toEdit";
    }

    /** 获取编辑的原始数据 */
    @ResponseBody
    @RequestMapping(path = "getEdit")
    public JsonResult getEdit(HttpServletRequest request){
        JsonResult result=new JsonResult();
        Goods goods = (Goods) request.getSession().getAttribute("goods");
        if(goods==null)
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        result.setData(goods);
        return result;
    }

    /** 执行编辑 */
    @ResponseBody
    @RequestMapping(path = "doEdit")
    public JsonResult doEdit(Goods goods,HttpServletRequest request){
        JsonResult result=new JsonResult();
        Goods old = (Goods) request.getSession().getAttribute("goods");
        goods.setId(old.getId());
        goods.setUpdateTime(new Date());
        int rows = service.editGoodsByID(goods);
        if(rows==0){
            result.setStatusCode(JsonResult.STATUS_ERROR);
        }else if(rows==-1){
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("类别设置错误！");
        }
        result.setData(rows);
        return result;
    }


}
