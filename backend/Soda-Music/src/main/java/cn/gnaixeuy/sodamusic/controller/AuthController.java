package cn.gnaixeuy.sodamusic.controller;

import cn.gnaixeuy.sodamusic.dto.ResponseResult;
import cn.gnaixeuy.sodamusic.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@RestController
@RequestMapping(value = {"/auth"})
public class AuthController {

    private AuthService authService;

    @GetMapping(value = "/sms-code/{phone}")
    public ResponseResult<Object> getPhoneValidateCode(@PathVariable String phone) {
        this.authService.getPhoneValidateCode(phone);
        return ResponseResult.success();
    }

    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }
}