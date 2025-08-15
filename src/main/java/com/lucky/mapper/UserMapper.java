package com.lucky.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lucky.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE tb_user SET money=money-#{money} WHERE id = #{id}")
    void deduction(@Param("id") Long id, @Param("money")Long money);

}
