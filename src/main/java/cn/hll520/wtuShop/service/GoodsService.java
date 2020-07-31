package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.Goods;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-29-9:19
 */
public interface GoodsService {

    List<Goods> getAll();

    PageInfo<Goods> searchGoods(Goods goods,Integer pageIndex,Integer pageSize);

    int addGoods(Goods goods);

    Goods getGoodsByID(Integer id);

    int delGoodsByID(Integer id);

    int editGoodsByID(Goods goods);
}
