package cn.hll520.wtuShop.controller.admin;

import cn.hll520.wtuShop.pojo.Category;
import cn.hll520.wtuShop.service.CategoryService;
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

/**
 * @author lpc
 * @create 2020-07-30-8:45
 */
@SuppressWarnings("all")
@Controller
@RequestMapping(path = "/admin/category/")
public class AdminCategoryController {

    @Autowired
    private CategoryService service;

    /**
     * 转发请求页面
     */
    @RequestMapping(path = "{method}")
    public String appforward(@PathVariable String method) {
        System.out.println(method);
        return "admin/category/" + method;
    }

    /**
     * 执行添加
     */
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

    /**
     * 模糊搜索
     * {@RequestParam 可以设置默认值} name是接收的参数名（默认和形参一致），defaultValue为默认值
     */
    @ResponseBody
    @RequestMapping(path = "doSearch")
    public JsonResult search(Category category, @RequestParam(name = "pageIndex", defaultValue = "1") Integer pageIndex, @RequestParam(defaultValue = "5") Integer pageSize) {
        JsonResult jsonResult = new JsonResult();
        PageInfo<Category> search = service.search(category, pageIndex, pageSize);

        if (search.getList().size() < 1)
            jsonResult.setStatusCode(JsonResult.STATUS_NOTFOUND);
        else
            jsonResult.setStatusCode(JsonResult.STATUS_SUCCESS_OK);
        jsonResult.setData(search);
        return jsonResult;
    }

    /**
     * 报错需要修改的内容到会话中  {@PathVariable 表示路径中的变量}，即占位符,name 为参数名称，默认和形参一致
     */
    @RequestMapping(path = "edit/{id}")
    public String toEdit(@PathVariable(name = "id") Integer id, HttpServletRequest request) {
        Category category = service.getCategoryByID(id);
        request.getSession().setAttribute("category", category);
        return "admin/category/edit";
    }

    /**
     * 获取当前正在编辑的内容
     */
    @ResponseBody
    @RequestMapping(path = "getEdit")
    public JsonResult getEdit(HttpServletRequest request) {
        JsonResult jsonResult = new JsonResult();
        Object category = request.getSession().getAttribute("category");
        if (category == null)
            jsonResult.setStatusCode(JsonResult.STATUS_ERROR);
        jsonResult.setData(category);
        return jsonResult;
    }

    /**
     * 执行修改
     */
    @ResponseBody
    @RequestMapping(path = "doEdit")
    public JsonResult doEdit(Category category, HttpServletRequest request) {

        System.out.println("____________" + category);

        JsonResult result = new JsonResult();
        Category old = (Category) request.getSession().getAttribute("category");
        category.setId(old.getId());
        int rows = service.editByID(category);
        if (rows == 1)
            request.getSession().removeAttribute("category");//成功修改则移除
        else if (rows == 0)
            result.setStatusCode(JsonResult.STATUS_ERROR);
        else {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("名称重复！");
        }
        result.setData(rows);
        return result;
    }

    /**
     * 执行删除
     */
    @ResponseBody
    @RequestMapping(path = "del/{id}")
    public JsonResult delCategory(@PathVariable Integer id) {
        JsonResult result = new JsonResult();
        int rows = service.delByID(id);
        if (rows == 1)
            result.setStatusCode(JsonResult.STATUS_SUCCESS_OK);
        else if (rows == 0) {
            result.setStatusCode(JsonResult.STATUS_NOTFOUND);
        } else {
            result.setStatusCode(JsonResult.STATUS_ERROR);
            result.setMsg("该类别下还有商品没有移除！");
        }
        result.setData(rows);
        return result;
    }

}
