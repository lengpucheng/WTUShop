package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.KillGoods;
import cn.hll520.wtuShop.pojo.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author lpc
 * @create 2020-08-03-9:45
 */
public interface KillGoodsService {

    /**
     * 不在秒杀时间段内
     */
    int NOT_KILL_TIME = -3;
    /**
     * 库存不足
     */
    int NOT_ENOUGH_NUMBER = -2;
    /**
     * 限购无法购买
     */
    int NOT_JUST_ONCE = -1;
    /**
     * 秒杀失败
     */
    int KILL_FAIL = 0;
    /**
     * 秒杀成功
     */
    int KILL_SUCCESS = 1;


    PageInfo<KillGoods> getAll(Integer pageIndex, Integer pageSize);

    KillGoods getKillGoodsByID(Integer id);

    int addKillGoods(KillGoods goods);

    int editKillGoodsByID(KillGoods goods);

    int kill(Integer killid, UserInfo userInfo);
}
