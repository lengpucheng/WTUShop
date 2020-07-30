package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.GoodsMapper;
import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-29-9:19
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper mapper;

    @Override
    public List<Goods> getAll() {
        List<Goods> goods;
        goods = mapper.selectByExample(null);
        return goods;
    }
}
