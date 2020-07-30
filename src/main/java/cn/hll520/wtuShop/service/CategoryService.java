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
}
