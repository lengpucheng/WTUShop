package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.CategoryMapper;
import cn.hll520.wtuShop.pojo.Category;
import cn.hll520.wtuShop.pojo.CategoryExample;
import cn.hll520.wtuShop.pojo.CategoryExample.Criteria;
import cn.hll520.wtuShop.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-30-8:55
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper mapper;

    @Transactional
    @Override
    public int addCategory(Category category) {

        /* 条件 */
        CategoryExample example = new CategoryExample();
        Criteria criteria = example.createCriteria();
        criteria.andNameEqualTo(category.getName());

        /* 检测名称 */
        List<Category> categories = mapper.selectByExample(example);

        /* 判断是否重复 */
        if (categories != null && categories.size() > 0) {
            return -1;
        }

        category.setGoodsNum(0);

        /* 加入 */
        return mapper.insert(category);

    }

    @Override
    public PageInfo<Category> search(Category category,int pageIndex,int pageSize) {
        CategoryExample example = new CategoryExample();
        if (category != null) {
            Criteria criteria = example.createCriteria();
            if (category.getName() != null && !category.getName().isEmpty())
                criteria.andNameLike("%" + category.getName() + "%");
        }
        PageHelper.startPage(pageIndex,pageSize);
        List<Category> categories = mapper.selectByExample(example);
        return new PageInfo<>(categories);
    }
}
