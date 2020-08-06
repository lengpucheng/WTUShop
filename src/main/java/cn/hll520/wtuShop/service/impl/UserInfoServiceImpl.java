package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.UserInfoMapper;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.UserInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author lpc
 * @create 2020-07-21-11:24
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper mapper;

    @Override
    public UserInfo login(UserInfo user) {
        UserInfo result = null;

        System.out.println(mapper);

        UserInfo userInfo = mapper.selectByUsername(user.getUsername());
        if (userInfo != null)
            if (userInfo.getPassword().equals(user.getPassword()))
                result = userInfo;


        return result;

    }

    @Transactional
    @Override
    public int register(UserInfo user) {
        int result;
        UserInfo userInfo = mapper.selectByUsername(user.getUsername());
        if (userInfo == null) {
            result = mapper.insertUser(user);
        } else {
            result = -1;
        }
        return result;
    }

    @Override
    public PageInfo<UserInfo> getAll(Integer pageIndex, Integer pageSize) {
        PageHelper.startPage(pageIndex, pageSize);
        List<UserInfo> userInfos = mapper.selectAll();
        return new PageInfo<>(userInfos);
    }

    @Override
    public int del(Integer id) {
        return mapper.deleteById(id);
    }

    @Override
    public int edit(UserInfo userInfo) {
        if(userInfo==null||userInfo.getPassword()==null||userInfo.getUsername()==null)
            return -1;
        return mapper.update(userInfo);
    }

    @Override
    public UserInfo getUserById(Integer id) {
        return mapper.selectByID(id);
    }
}
