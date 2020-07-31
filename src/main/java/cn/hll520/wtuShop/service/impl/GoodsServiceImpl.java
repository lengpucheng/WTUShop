package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.CategoryMapper;
import cn.hll520.wtuShop.mapper.GoodsMapper;
import cn.hll520.wtuShop.pojo.Category;
import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.pojo.GoodsExample;
import cn.hll520.wtuShop.service.GoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-29-9:19
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Goods> getAll() {
        List<Goods> goods;
        goods = mapper.selectByExample(null);
        return goods;
    }

    @Override
    public PageInfo<Goods> searchGoods(Goods goods, Integer pageIndex, Integer pageSize) {

        GoodsExample example = new GoodsExample();
        if (goods != null) {
            example.createCriteria().andNameLike("%" + goods.getName() + "%");
        }
        PageHelper.startPage(pageIndex, pageSize);
        List<Goods> goodsList = mapper.selectByExample(example);
        return new PageInfo<>(goodsList);
    }

    @Transactional
    @Override
    public int addGoods(Goods goods) {
        int rows;
        /* 判断商品类别是否设置 */
        if (goods.getCategoryId() == null)
            return -1;
        /* 解析商品类别 */
        Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());
        if (category == null)
            return -1;

        /* 插入商品 */
        rows = mapper.insert(goods);
        /* 刷新类别数量 */
        category.setGoodsNum(category.getGoodsNum() + 1);
        int i = categoryMapper.updateByPrimaryKey(category);
        if (i < 1)
            return -1;

        return rows;
    }

    @Override
    public Goods getGoodsByID(Integer id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Transactional
    @Override
    public int delGoodsByID(Integer id) {
        Goods goods = mapper.selectByPrimaryKey(id);
        Category category = categoryMapper.selectByPrimaryKey(goods.getCategoryId());

        int rows = mapper.deleteByPrimaryKey(id);
        /* 更新数量 */
        category.setGoodsNum(category.getGoodsNum() - 1);
        categoryMapper.updateByPrimaryKey(category);

        return rows;
    }

    @Transactional
    @Override
    public int editGoodsByID(Goods goods) {
        /* 如果类别为空就返回错误 */
        if (goods.getCategoryId() == null || categoryMapper.selectByPrimaryKey(goods.getCategoryId()) == null)
            return -1;
        return mapper.updateByPrimaryKey(goods);
    }
}
