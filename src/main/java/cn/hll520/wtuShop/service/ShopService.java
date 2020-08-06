package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.Shop;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-21:16
 */
public interface ShopService {

    /** 没有找到 */
    String NOT_FOUND="not_found";
    /** 提交失败 */
    String SUBMIT_FAILED="submit_failed";
    /** 权限不足 */
    String NOT_ADMIN="not_admin";
    /** 发生错误 */
    String ERROR="error";

    /** 出现错误 */
    int HAPPEN_ERROR=-3;
    /** 库存不足 */
    int NOT_MOTHER_SUM=-2;
    /** 权限不足 */
    int NOT_SA=-1;
    /** 失败 */
    int NOT_SUCCESS=0;

    /** 获取提交订单的错误信息
     *  其中  shopId  保存错误的id
     *  goods.name 保存错误的内容提示
     *  goods.goodsID 保存错误的商品ID
     *
     * */
    Shop getError();

    /**
     * 添加
     */
    int add(Integer userId, Integer goodsID);

    /**
     * 查询全部管理员
     */
    PageInfo<Shop> showAdmin(Integer pageIndex, Integer pageSize);

    /**
     * 查询全部
     */
    List<Shop> show(Integer userID);

    /**
     * 移除
     */
    int del(Integer userId, Integer shopID);

    /** 管理员删除 */
    int delAdmin(Integer shopID);

    /**
     * 修改数量
     */
    int edit(Integer userID, Integer shopID, int sum);

    /**
     * 生成订单
     */
    String makeOrder(Integer UserID);


}
