package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.UserInfo;
import com.github.pagehelper.PageInfo;

/**
 * @author lpc
 * @create 2020-07-21-11:21
 */
public interface UserInfoService {
    UserInfo login(UserInfo user);

    UserInfo getUserById(Integer id);

    int register(UserInfo user);

    PageInfo<UserInfo> getAll(Integer pageIndex,Integer pageSize);

    int del(Integer id);

    int edit(UserInfo userInfo);
}
