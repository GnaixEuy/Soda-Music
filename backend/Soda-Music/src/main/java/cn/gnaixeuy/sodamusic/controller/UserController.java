package cn.gnaixeuy.sodamusic.controller;

import cn.gnaixeuy.sodamusic.dto.ResponseResult;
import cn.gnaixeuy.sodamusic.entity.user.UserEntity;
import cn.gnaixeuy.sodamusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    private UserService userService;

    @GetMapping(value = {"/list"})
    public ResponseResult<List<UserEntity>> getAll() {
        return ResponseResult.success(this.userService.testUserList());
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}