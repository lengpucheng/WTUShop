package cn.hll520.wtuShop.mapper;

import cn.hll520.wtuShop.pojo.KillOrder;
import cn.hll520.wtuShop.pojo.KillOrderExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface KillOrderMapper {
    long countByExample(KillOrderExample example);

    int deleteByExample(KillOrderExample example);

    int deleteByPrimaryKey(Integer killOrderId);

    int insert(KillOrder record);

    int insertSelective(KillOrder record);

    List<KillOrder> selectByExample(KillOrderExample example);

    KillOrder selectByPrimaryKey(Integer killOrderId);

    int updateByExampleSelective(@Param("record") KillOrder record, @Param("example") KillOrderExample example);

    int updateByExample(@Param("record") KillOrder record, @Param("example") KillOrderExample example);

    int updateByPrimaryKeySelective(KillOrder record);

    int updateByPrimaryKey(KillOrder record);
}