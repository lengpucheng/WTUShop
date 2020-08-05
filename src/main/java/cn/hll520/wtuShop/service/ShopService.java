package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.Shop;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-21:16
 */
public interface ShopService {

    /**
     * 添加
     */
    int add(Integer userId, Integer goodsID);

    /**
     * 查询全部管理员
     * @return
     */
    PageInfo<Shop> showAdmin(Integer pageIndex, Integer pageSize);

    /**
     * 查询全部
     */
    List<Shop> show(Integer userID);

    /**
     * 移除
     */
    int del(Integer userId,Integer shopID);

    /**
     * 修改数量
     */
    int edit(Integer userID, Integer shopID, int sum);

    /**
     * 生成订单
     */
    String makeOrder(Integer UserID);
}
