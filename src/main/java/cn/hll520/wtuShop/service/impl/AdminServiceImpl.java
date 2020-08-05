package cn.hll520.wtuShop.service.impl;

import cn.hll520.wtuShop.mapper.AdminMapper;
import cn.hll520.wtuShop.mapper.UserInfoMapper;
import cn.hll520.wtuShop.pojo.Admin;
import cn.hll520.wtuShop.pojo.AdminExample;
import cn.hll520.wtuShop.pojo.UserInfo;
import cn.hll520.wtuShop.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lpc
 * @create 2020-08-05-14:45
 */
@SuppressWarnings("all")
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper mapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Admin login(Admin admin) {
        AdminExample example = new AdminExample();
        example.createCriteria()
                .andUsernameEqualTo(admin.getUsername())
                .andPasswordEqualTo(admin.getPassword());
        List<Admin> admins = mapper.selectByExample(example);

        if (admins.size() < 1)
            return null;
        Admin sa = admins.get(0);
        UserInfo userInfo = userInfoMapper.selectByID(sa.getUserid());

        userInfo.setPassword("******");
        sa.setUserInfo(userInfo);
        sa.setPassword("******");

        return sa;
    }
}
