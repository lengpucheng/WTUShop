package cn.hll520.wtuShop.mapper;

import cn.hll520.wtuShop.pojo.KillGoods;
import cn.hll520.wtuShop.pojo.KillGoodsExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KillGoodsMapper {
    long countByExample(KillGoodsExample example);

    int deleteByExample(KillGoodsExample example);

    int deleteByPrimaryKey(Integer killId);

    int insert(KillGoods record);

    int insertSelective(KillGoods record);

    List<KillGoods> selectByExample(KillGoodsExample example);

    List<KillGoods> selectAllKillGoods();


    KillGoods selectAllKillGoodsByID(Integer killId);


    KillGoods selectByPrimaryKey(Integer killId);

    int updateByExampleSelective(@Param("record") KillGoods record, @Param("example") KillGoodsExample example);

    int updateByExample(@Param("record") KillGoods record, @Param("example") KillGoodsExample example);

    int updateByPrimaryKeySelective(KillGoods record);

    int updateByPrimaryKey(KillGoods record);
}