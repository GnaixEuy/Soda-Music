package cn.gnaixeuy.sodamusic.service;

import cn.gnaixeuy.sodamusic.entity.user.UserEntity;

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
public interface UserService {

    List<UserEntity> getAllList();

}