package cn.gnaixeuy.sodamusic.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@AllArgsConstructor
@Getter
public enum ExceptionType {

    /**
     * 枚举业务异常信息
     */
    INNER_ERROR(500, "系统内部错误"),
    UNAUTHORIZED(401, "未登录"),
    BAD_REQUEST(400, "请求错误"),
    FORBIDDEN(403, "无权操作"),
    NOT_FOUND(404, "未找到"),
    PHONE_EMPTY(30001001, "手机号不能为空"),
    USER_NAME_DUPLICATE(40001001, "用户名重复"),
    USER_NOT_FOUND(40401002, "用户不存在"),
    USER_PASSWORD_NOT_MATCH(40001003, "用户名或密码错误"),
    USER_NOT_ENABLED(50001001, "用户未启用"),
    USER_LOCKED(50001002, "用户被锁定"),
    USER_OPEN_ID_NOT_FOUND(40401003, "未找到openId绑定用户"),
    MUSIC_NOT_FOUND(40402001, "歌曲不存在"),
    FILE_NOT_FOUND(40403001, "文件不存在"),
    FILE_NOT_PERMISSION(40303002, "当前用户无权限修改文件"),
    PLAYLIST_NOT_FOUND(40404001, "歌单不存在"),
    ARTIST_NOT_FOUND(40405001, "歌手不存在"),
    ALBUM_NOT_FOUND(40406001, "专辑不存在");

    private final Integer code;
    private final String message;

}