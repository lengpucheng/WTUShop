package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.OrderMapper;
import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.OrderExample;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.OrderService;
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
 * @create 2020-08-05-11:22
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper mapper;

    @Override
    public PageInfo<Order> getAll(Integer pageIndex,Integer pageSize) {
        PageHelper.startPage(pageIndex,pageSize);
        List<Order> orders = mapper.selectByExample(null);
        return new PageInfo<>(orders);
    }

    @Override
    public List<List<Order>> getAllByUserID(Integer userID, Integer pageIndex, Integer pageSize) {

        PageHelper.startPage(pageIndex,pageSize);
        List<String> orderKey = getOrderKeyById(userID);
        System.out.println(orderKey);

        List<List<Order>> list=new ArrayList<>();
        for(String key:orderKey){
            OrderExample example=new OrderExample();
            example.createCriteria().andOrderKeyEqualTo(key);
            List<Order> orders = mapper.selectByExample(example);
            list.add(orders);
        }
        System.out.println(list);

        return list;
    }

    @Override
    public List<String> getOrderKeyById(Integer userID) {

        return mapper.selectOrderKeyByID(userID);
    }

    @Override
    public List<Order> getOrderByKey(String OrderKey) {
        OrderExample example=new OrderExample();
        example.createCriteria().andOrderKeyEqualTo(OrderKey);
        return mapper.selectByExample(example);
    }

    @Transactional
    @Override
    public int payOrder(String orderKey, UserInfo userInfo, Integer payPrice) {
        if (userInfo==null)
            return NOT_LOGIN;

        List<Order> orderByKey = getOrderByKey(orderKey);

        /* 计算总价 */
        int price=0;
        for(Order order:orderByKey){
            price+=order.getGoodsPrice()*order.getGoodsCount();
        }

        /* 比较支付金额 */
        if(price>payPrice)
            return NOT_PAY_PRICE;

        Order order=new Order();
        order.setOrderStatus("支付成功");
        order.setPayDate(new Date());

        /* 修改支付状态 */
        OrderExample example=new OrderExample();
        example.createCriteria().andOrderKeyEqualTo(orderKey);
        int rows = mapper.updateByExampleSelective(order, example);

        /* 判断修改状态 */
        if(rows<1)
            return PAY_FAIL;

        return PAY_SUCCESS;
    }

    @Override
    public int delOrder(String orderKey) {
        OrderExample example=new OrderExample();
        example.createCriteria().andOrderKeyEqualTo(orderKey);
        return mapper.deleteByExample(example);
    }
}
