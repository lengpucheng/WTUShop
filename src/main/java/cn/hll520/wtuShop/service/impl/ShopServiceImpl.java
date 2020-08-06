package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.GoodsMapper;
import cn.hll520.wtuShop.mapper.OrderMapper;
import cn.hll520.wtuShop.mapper.ShopMapper;
import cn.hll520.wtuShop.pojo.Goods;
import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.Shop;
import cn.hll520.wtuShop.pojo.ShopExample;
import cn.hll520.wtuShop.service.ShopService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
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
    @Autowired
    private OrderMapper orderMapper;

    @Transactional
    @Override
    public int add(Integer userId, Integer goodsID) {
        /* 获取商品 */
        Goods goods = goodsMapper.selectByPrimaryKey(goodsID);
        if (goods == null)
            return HAPPEN_ERROR;

        System.out.println("_____"+goods);

        /* 查看该商品是否已经存在购物车 */
        ShopExample example = new ShopExample();
        example.createCriteria().andUserIdEqualTo(userId).andGoodsIdEqualTo(goodsID);
        List<Shop> shops = mapper.selectByExample(example);

        System.out.println("__shop___"+shops);

        /* 如果存在就将数量增加1 */
        if (shops.size() > 0) {
            Shop shop = shops.get(0);
            return edit(userId, shop.getShoppingId(), shop.getGoodsSum() + 1);
        }

        /* 生成购物车 */
        Shop shop = new Shop();
        shop.setUserId(userId);
        shop.setGoodsId(goodsID);
        shop.setGoodsSum(1);
        /* 添加 */
        return mapper.insertSelective(shop);
    }

    @Override
    public PageInfo<Shop> showAdmin(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<Shop> shops = mapper.selectByExample(null);
        PageInfo<Shop> pageInfo = new PageInfo<>(shops);
        for (Shop shop : shops) {
            Goods goods = goodsMapper.selectByPrimaryKey(shop.getGoodsId());
            shop.setGoods(goods);
        }
        return pageInfo;
    }

    @Override
    public List<Shop> show(Integer userID) {
        ShopExample example = new ShopExample();
        example.createCriteria().andUserIdEqualTo(userID);
        List<Shop> shops = mapper.selectByExample(example);
        for (Shop shop : shops) {
            Goods goods = goodsMapper.selectByPrimaryKey(shop.getGoodsId());
            shop.setGoods(goods);
        }
        return shops;
    }

    @Override
    public int del(Integer userId, Integer shopID) {
        /* 获取商品 */
        Shop shop = mapper.selectByPrimaryKey(shopID);

        /* 判断权限 */
        if (shop == null || !Objects.equals(userId, shop.getUserId()))
            return NOT_SA;

        return mapper.deleteByPrimaryKey(shopID);
    }

    @Override
    public int delAdmin(Integer shopID) {
        return mapper.deleteByPrimaryKey(shopID);
    }

    @Override
    public int edit(Integer userID, Integer shopID, int sum) {
        /* 获取购物车内容 */
        Shop shop = mapper.selectByPrimaryKey(shopID);
        /* 判断权限 */
        if (shop == null || !Objects.equals(shop.getUserId(), userID))
            return NOT_SA;
        /* 设置购物车和商品之后的数量 */
        shop.setGoodsSum(sum);
        /* 更新商品数量 */
        return mapper.updateByPrimaryKeySelective(shop);

    }

    @Transactional
    @Override
    public String makeOrder(Integer UserID) {
        /* 获取购物车商品 */
        ShopExample example = new ShopExample();
        example.createCriteria().andUserIdEqualTo(UserID);
        List<Shop> shops = mapper.selectByExample(example);

        /* 获取当前时间戳和订单号 */
        Date date = new Date();
        String orderKey = UserID + "_" + new Date().getTime();

        /* 将每一项添加订单 */
        for (Shop shop : shops) {

            /* 获取商品 */
            Goods goods = goodsMapper.selectByPrimaryKey(shop.getGoodsId());

            /* 比较库存 */
            if (goods.getNum() < shop.getGoodsSum()) {
                goods.setName("库存不足！");
                shop.setGoods(goods);
                errorShop = shop;
                return ERROR;
            }

            /* 修改库存 */
            goods.setNum(goods.getNum() - shop.getGoodsSum());
            int goodsEdit = goodsMapper.updateByPrimaryKeySelective(goods);
            if (goodsEdit < 1) {
                goods.setName("写入订单失败");
                shop.setGoods(goods);
                errorShop = shop;
                return ERROR;
            }

            /* 生成该商品订单 */
            Order order = new Order();
            order.setOrderKey(orderKey);
            order.setUserId(UserID);
            order.setOrderStatus("未支付");
            order.setCreateDate(date);
            order.setGoodsId(goods.getId());
            order.setGoodsName(goods.getName());
            order.setGoodsPrice(goods.getPrice());
            order.setGoodsCount(shop.getGoodsSum());

            /* 写入订单 */
            int orderNew = orderMapper.insertSelective(order);
            if (orderNew < 1) {
                goods.setName("写入订单失败");
                shop.setGoods(goods);
                errorShop = shop;
                return ERROR;
            }

            /* 删除购物车的该商品 */
            int shopDel = mapper.deleteByPrimaryKey(shop.getShoppingId());
            if (shopDel < 1) {
                goods.setName("创建订单失败");
                shop.setGoods(goods);
                errorShop = shop;
                return ERROR;
            }
        }

        return orderKey;
    }

    private Shop errorShop;

    @Override
    public Shop getError() {
        return errorShop;
    }
}
