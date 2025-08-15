package com.lucky.admin;

import cn.hutool.core.bean.BeanUtil;
import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.lucky.domain.dto.Result;
import com.lucky.domain.dto.UserDTO;
import com.lucky.domain.po.User;
import com.lucky.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@Tag(name = "管理员接口")
@EnableKnife4j
@RequestMapping("admin")
public class AdminController {
    @Resource
    private IUserService userService;

    @Operation(summary = "新增用户接口")
    @PostMapping("/add")
    public void saveUser(@RequestBody UserDTO userDTO) {
        //1.dto拷贝到po
        User user = BeanUtil.copyProperties(userDTO, User.class);
        userService.save(user);
    }

    @Operation(summary = "删除用户接口")
    @DeleteMapping("{id}")
    public void deleteUserById(@Parameter(name = "id",in = ParameterIn.PATH) @PathVariable Long id) {
        userService.removeById(id);
    }


    @Operation(summary = "根据id查询用户接口")
    @GetMapping("{id}")
    public Result queryUserById( @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH)
                                @PathVariable("id") Long id) {
        //1.查询用户
        User user = userService.getById(id);
        //2.拷贝
        return  Result.ok(BeanUtil.copyProperties(user,UserDTO.class));
    }

    @Operation(summary = "根据id查询一些用户接口")
    @GetMapping
    public List<User> queryUserByIds(@Parameter(name = "用户id集合") @RequestParam("ids") List<Long> ids) {
        //1.查询用户
        List<User> users = userService.listByIds(ids);
        //2.拷贝
        return BeanUtil.copyToList(users,User.class);
    }


    @Operation(summary = "扣减用户余额接口")
    @PutMapping("/{id}/deduction/{money}")
    public void deductMoneyById(
            @Parameter(name = "id",description = "用户id",in = ParameterIn.PATH) @PathVariable("id") Long id,
            @ApiParam(name = "金额")@PathVariable("money") Long money) {
        //1.dto拷贝到to
        userService.deductmoney(id,money);
    }
}
