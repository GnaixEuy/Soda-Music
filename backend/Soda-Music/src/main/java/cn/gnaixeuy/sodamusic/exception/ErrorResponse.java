package cn.gnaixeuy.sodamusic.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {

    private Integer code;
    private String msg;
    private Object trace;

}