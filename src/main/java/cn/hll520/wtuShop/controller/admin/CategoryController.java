package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Category;
import cn.hll520.wtuShop.service.CategoryService;
import cn.hll520.wtuShop.utils.JsonResult;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lpc
 * @create 2020-07-30-8:45
 */
@Controller
@RequestMapping(path = "/admin/category/")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @RequestMapping(path = "toAdd")
    public String toAdd() {
        return "admin/category_toAdd";
    }

    @RequestMapping(path = "doAdd")
    @ResponseBody
    public JsonResult doAdd(Category category, HttpServletResponse response) {
        int result = service.addCategory(category);


        JsonResult jsonResult = new JsonResult();
        jsonResult.setData(result);

        if (result == 1) {
            jsonResult.setStatusCode(JsonResult.STATUS_SUCCESS_OK);
        } else if (result == 0) {
            jsonResult.setStatusCode(JsonResult.STATUS_ERROR);
            jsonResult.setMsg("添加失败！");
        } else {
            jsonResult.setStatusCode(JsonResult.STATUS_ERROR);
            jsonResult.setMsg("添加失败！类别名称重复");
        }

        return jsonResult;
    }

    @RequestMapping(path = "search")
    public String search() {
        return "admin/category_search";
    }


    /* @RequestParam可以设置默认值 name是接收的参数名（默认和形参一致），defaultValue为默认值  */
    @ResponseBody
    @RequestMapping(path = "doSearch")
    public JsonResult search(Category category, @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult jsonResult = new JsonResult();
        PageInfo<Category> search = service.search(category, pageIndex, pageSize);
//        if(search.size()<1){
//            jsonResult.setStatusCode(500);
//            jsonResult.setMsg("查询失败");
//        }else{
//            jsonResult.setStatusCode(200);
//        }
        if (search.getList().size() < 1)
            jsonResult.setStatusCode(JsonResult.STATUS_NOTFOUND);
        else
            jsonResult.setStatusCode(JsonResult.STATUS_SUCCESS_OK);
        jsonResult.setData(search);
        return jsonResult;
    }



}
