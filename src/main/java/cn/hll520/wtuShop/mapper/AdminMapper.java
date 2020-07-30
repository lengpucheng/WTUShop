package cn.hll520.wtuShop.mapper;

import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.pojo.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@SuppressWarnings("all")
public interface AdminMapper {
    long countByExample(AdminExample example);

    int deleteByExample(AdminExample example);

    int deleteByPrimaryKey(Integer adminid);


    /**
     * 给所有字段都增加，如果为null就写为null
     * @param admin admin对象
     * @return 插入的条数
     */
    int insert(Admin admin);

    /**
     * 给已经赋值的字段增加，为null则留空
     * @param record    admin对象
     * @return  插入的条数
     */
    int insertSelective(Admin record);

    List<Admin> selectByExample(AdminExample example);

    Admin selectByPrimaryKey(Integer adminid);

    /**
     * 根据条件去修改部分字段 为null不修改
     * @param record    修改的对象
     * @param example   条件
     * @return  修改的条数
     */
    int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * 根据条件去修改所有字段 为null 则赋值null
     * @param record    修改的对象
     * @param example   条件
     * @return  修改的条数
     */
    int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

    /**
     * 根据主键修改 修改部分字段 为null则不修改
     * @param record    修改的对象
     * @return  修改条数
     */
    int updateByPrimaryKeySelective(Admin record);

    /**
     * 根据主键修改 修改全部字段 为null 则赋值null
     * @param record    修改的对象
     * @return  修改条数
     */
    int updateByPrimaryKey(Admin record);
}