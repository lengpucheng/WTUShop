package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.GoodsMapper;
import cn.hll520.wtuShop.mapper.ShopMapper;
import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.pojo.Shop;
import cn.hll520.wtuShop.pojo.ShopExample;
import cn.hll520.wtuShop.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author lpc
 * @create 2020-08-05-21:20
 */
@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    private ShopMapper mapper;
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public int add(Integer userId, Integer goodsID) {
        Shop shop = new Shop();
        shop.setUserId(userId);
        shop.setGoodsId(goodsID);
        return mapper.insertSelective(shop);
    }

    @Override
    public PageInfo<Shop> showAdmin(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Shop> shops = mapper.selectByExample(null);
        PageInfo<Shop> pageInfo=new PageInfo<>(shops);
        for (Shop shop : shops) {
            Goods goods = goodsMapper.selectByPrimaryKey(shop.getGoodsId());
            shop.setGoods(goods);
        }
        return pageInfo;
    }

    @Override
    public List<Shop> show(Integer userID) {
        ShopExample example=new ShopExample();
        example.createCriteria().andUserIdEqualTo(userID);
        List<Shop> shops = mapper.selectByExample(example);
        for (Shop shop : shops) {
            Goods goods = goodsMapper.selectByPrimaryKey(shop.getGoodsId());
            shop.setGoods(goods);
        }
        return shops;
    }

    @Override
    public int del(Integer userId,Integer shopID) {
        Shop shop = mapper.selectByPrimaryKey(shopID);
        if(!Objects.equals(userId, shop.getUserId()))
            return -1;
        return mapper.deleteByPrimaryKey(shopID);
    }

    @Override
    public int delAdmin(Integer shopID) {
        return mapper.deleteByPrimaryKey(shopID);
    }

    @Override
    public int edit(Integer userID, Integer shopID, int sum) {
        Shop shop = mapper.selectByPrimaryKey(shopID);
        if(!Objects.equals(shop.getUserId(), userID))
            return -1;
        shop.setGoodsSum(sum);
        return mapper.updateByPrimaryKey(shop);
    }

    @Override
    public String makeOrder(Integer UserID) {
        return null;
    }
}
