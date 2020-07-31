package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.Category;
import com.github.pagehelper.PageInfo;

/**
 * @author lpc
 * @create 2020-07-30-8:54
 */

public interface CategoryService {
    /**
     * 添加一个商品类型
     * @param category  商品类型对象
     * @return  添加的数量
     */
    int addCategory(Category category);

    /**
     * 搜索
     * @param category 搜索条件
     * @param pageIndex 页数
     * @param pageSize  每页数量
     * @return  商品类别集合
     */
    PageInfo<Category> search(Category category, int pageIndex, int pageSize);

    /**
     * 根据ID获取类别信息
     * @param id ID
     * @return  类别对象
     */
    Category getCategoryByID(Integer id);

    /**
     * 根据ID修改
     * @param category 修改的对象
     * @return 修改的条数
     */
    int editByID(Category category);

    /**
     * 根据 ID 删除
     * @param integer ID
     * @return 删除的条数
     */
    int delByID(Integer integer);
}
