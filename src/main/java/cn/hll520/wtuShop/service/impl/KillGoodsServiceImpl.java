package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.KillGoodsMapper;
import cn.hll520.wtuShop.pojo.KillGoods;
import cn.hll520.wtuShop.service.KillGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-03-9:45
 */
@Service
public class KillGoodsServiceImpl implements KillGoodsService {

    @Autowired
    private KillGoodsMapper mapper;

    @Override
    public PageInfo<KillGoods> getAll(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<KillGoods> killGoods = mapper.selectAllKillGoods();
        return new PageInfo<>(killGoods);
    }

    @Override
    public KillGoods getKillGoodsByID(Integer id) {
        return mapper.selectAllKillGoodsByID(id);
    }

    @Override
    public int addKillGoods(KillGoods goods) {
        return mapper.insert(goods);
    }

    @Override
    public int editKillGoodsByID(KillGoods goods) {
        return mapper.updateByPrimaryKeySelective(goods);
    }
}
