package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.KillGoods;
import com.github.pagehelper.PageInfo;

/**
 * @author lpc
 * @create 2020-08-03-9:45
 */
public interface KillGoodsService {
    PageInfo<KillGoods> getAll(Integer pageIndex,Integer pageSize);

    KillGoods getKillGoodsByID(Integer id);

    int addKillGoods(KillGoods goods);

    int editKillGoodsByID(KillGoods goods);
}
