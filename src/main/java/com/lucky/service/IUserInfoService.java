package com.lucky.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lucky.domain.dto.Result;
import com.lucky.domain.po.UserInfo;
import com.lucky.domain.vo.UserInfoVO;

public interface IUserInfoService extends IService<UserInfo>{

    Result userInfo(Long id);

    Result saveMassage(UserInfo userInfo);
}
