package cn.hll520.wtuShop.mapper;

import cn.hll520.wtuShop.pojo.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author lpc
 * @create 2020-07-21-10:53
 * 包名+接口名 同 Mapper命名空间对应
 * 接口名  同 Mapper的索引ID对象
 * 参数类型  和 parameterType对应
 * 返回类型  和 resultType 对应
 *
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 根据ID查询用户信息
     * @param id 用户ID
     * @return  一个用户对象，否则返回null
     */
    UserInfo selectByID(int id);

    /**
     * 根据用户名查询用户信息
     * @param username 用户名
     * @return  一个用户对象，否则返回null
     */
    UserInfo selectByUsername(String username);

    /**
     * 查询所有用户信息
     * @return 返回全部用户信息
     */
    List<UserInfo> selectAll();

    /**
     * 根据 KEY 进行模糊查询，并分页返回结果
     * @param map   map{starIndex: int,起始的索引数，pageSize：int ,每页数量，key：Userinfo 模糊查询的条件}
     * @return  当页的用户信息
     */
    List<UserInfo> searchLimit(Map<String,Object> map);


    /**
     * 获取总数量
     * @return  总数量
     */
    int selectCount();

    /**
     * 保存一个用户信息
     * @param userInfo 用户信息，不需要ID
     * @return  有影响的条数
     */
    int insertUser(UserInfo userInfo);

    /**
     * 更新一个用户信息
     * @param userInfo 用户信息，需要ID
     * @return 更改的条数
     */
    int update(UserInfo userInfo);

    /**
     * 通过ID删除一个用户
     * @param id    用户ID
     * @return  删除的条数
     */
    int deleteById(int id);

}
