package cn.gnaixeuy.sodamusic.service.impl;

import cn.gnaixeuy.sodamusic.entity.user.UserEntity;
import cn.gnaixeuy.sodamusic.enums.ExceptionType;
import cn.gnaixeuy.sodamusic.exception.BizException;
import cn.gnaixeuy.sodamusic.repository.UserRepository;
import cn.gnaixeuy.sodamusic.service.AuthService;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Slf4j
@Service
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> userEntityOptional = this.userRepository.findByUsername(username);
        if (userEntityOptional.isEmpty()) {
            throw new BizException(ExceptionType.USER_NOT_FOUND);
        }
        UserEntity userEntity = userEntityOptional.get();
        if (userEntity.getLocked()) {
            throw new BizException(ExceptionType.USER_LOCKED);
        }
        if (!userEntity.getEnabled()) {
            throw new BizException(ExceptionType.USER_NOT_ENABLED);
        }
        return userEntity;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String getPhoneValidateCode(String phone) {
        //todo 这里后面封request 请求 去校验ip
        log.info("本次请求手机验证码的手机号为:{}", phone);
        if (StrUtil.isBlank(phone)) {
            throw new BizException(ExceptionType.PHONE_EMPTY);
        }
        // 发送验证码

        //存入redis
        
        return null;
    }
}