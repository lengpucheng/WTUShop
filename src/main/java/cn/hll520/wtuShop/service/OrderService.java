package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.Order;
import cn.hll520.wtuShop.pojo.UserInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-10:55
 */

public interface OrderService {

    /** 没有登录 */
    int NOT_LOGIN=-2;

    /** 金额不足 */
    int NOT_PAY_PRICE=-1;
    /** 支付失败 */
    int PAY_FAIL=0;
    /** 支付成功 */
    int PAY_SUCCESS=1;

    PageInfo<Order> getAll(Integer pageIndex,Integer pageSize);

    PageInfo<Order> getAllByUserID(Integer userID,Integer pageIndex,Integer pageSize);

    List<Order> getOrderByKey(String OrderKey);

    int payOrder(String orderKey,UserInfo userInfo,Integer payPrice);

    int delOrder(String orderKey);
}
