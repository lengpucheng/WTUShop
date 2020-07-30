package cn.hll520.wtuShop.service;

import cn.hll520.wtuShop.pojo.UserInfo;

/**
 * @author lpc
 * @create 2020-07-21-11:21
 */
public interface UserInfoService {
    UserInfo login(UserInfo user);

    int register(UserInfo user);
}
