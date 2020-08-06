package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.KillGoodsMapper;
import cn.hll520.wtuShop.mapper.KillOrderMapper;
import cn.hll520.wtuShop.mapper.OrderMapper;
import cn.hll520.wtuShop.pojo.*;
import cn.hll520.wtuShop.service.KillGoodsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lpc
 * @create 2020-08-03-9:45
 */
@Service
public class KillGoodsServiceImpl implements KillGoodsService {

    @Autowired
    private KillGoodsMapper mapper;
    @Autowired
    private KillOrderMapper killOrderMapper;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public PageInfo<KillGoods> getAll(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
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

    @Transactional
    @Override
    public int kill(Integer killid, UserInfo userInfo) {
        if (userInfo == null)
            return -9;

        /* 1.  获取秒杀的商品 */
        KillGoods goods = mapper.selectAllKillGoodsByID(killid);

        System.out.println("__商品获取___" + goods);

        /* 2. 判断是否结束或开始 */
        long min = goods.getDateStart().getTime();
        long max = goods.getDateEnd().getTime();
        long now = System.currentTimeMillis();
        if (now < min && now > max)
            return NOT_KILL_TIME;

        System.out.println("__完成 2___");

        /* 3 .判断库存 */
        if (goods.getStockCount() <= 0)
            return NOT_ENOUGH_NUMBER;

        System.out.println("__完成 3___");


        /* 4. 是否已经创建该活动 */
        KillOrderExample killOrderExample = new KillOrderExample();
        killOrderExample.createCriteria()
                .andKillUserIdEqualTo(userInfo.getUserid())
                .andKillGoodsIdEqualTo(goods.getGoodsId());
        long sum = killOrderMapper.countByExample(killOrderExample);
        if (sum != 0)
            return NOT_JUST_ONCE;
        System.out.println("__完成 4___");


        /* 5. 创建订单和秒杀订单 */
        Order order = new Order();
        order.setGoodsId(goods.getGoodsId());
        //设置订单Key
        order.setOrderKey(userInfo.getUserid() + "_" + now);
        order.setUserId(userInfo.getUserid());
        order.setGoodsCount(1);
        order.setGoodsName(goods.getName());
        order.setOrderStatus("未支付");
        order.setGoodsPrice(goods.getKillPrice());
        order.setCreateDate(new Date());

        System.out.println("__order__" + order);

        if (orderMapper.insertSelective(order) == 1) {

            System.out.println("__完成 5.11___");
            System.out.println("__order2__" + order);
            //加入秒杀订单
            KillOrder killOrder = new KillOrder();
            killOrder.setKillGoodsId(goods.getGoodsId());
            killOrder.setOrderId(order.getOrderId());
            killOrder.setKillUserId(userInfo.getUserid());
            if (killOrderMapper.insert(killOrder) == 1) {

                System.out.println("__完成 5.2___");

                /* 6. 修改库存 */
                goods.setStockCount(goods.getStockCount() - 1);
                int upRows = mapper.updateByPrimaryKeySelective(goods);
                if (upRows == 1)
                    return killOrder.getKillOrderId();
            }
        }

        return KILL_FAIL;
    }

    @Override
    public List<Order> killOrderInfo(Integer killOrderId) {
        Order order = orderMapper.selectByKillOrderId(killOrderId);
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        return orders;
    }

    @Override
    public int del(Integer killid) {
        return mapper.deleteByPrimaryKey(killid);
    }
}
