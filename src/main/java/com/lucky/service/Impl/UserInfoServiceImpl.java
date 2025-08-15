package com.lucky.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lucky.domain.dto.Result;
import com.lucky.domain.po.User;
import com.lucky.domain.po.UserInfo;
import com.lucky.domain.vo.UserInfoVO;
import com.lucky.mapper.UserInfoMapper;
import com.lucky.service.IUserInfoService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {
    @Override
    public Result userInfo(Long id) {
        UserInfo userinfo = getById(id);
        if (userinfo == null) {
            userinfo= new UserInfo();
            userinfo.setId(id);
            save(userinfo);
        }

        return Result.ok(userinfo);
    }

    @Override
    public Result saveMassage(UserInfo userInfo) {
        if(userInfo==null){
            return Result.fail("更新数据不存在");
        }
        UserInfo userinfo = getById(userInfo.getId());
        if (userinfo == null) {
            return Result.fail("用户不存在");
        }
        BeanUtils.copyProperties(userInfo, userinfo);
       /* user.setUpdateTime(LocalDateTime.now());*/
        /*lambdaUpdate()
                .set(UserInfo::getUserName, userInfo.getUserName())
                .set(UserInfo::getCity,userInfo.getCity())
                .set(UserInfo::getIntroduce,userInfo.getIntroduce())

                .update();*/
        return Result.ok(updateById(userinfo));
    }
}
