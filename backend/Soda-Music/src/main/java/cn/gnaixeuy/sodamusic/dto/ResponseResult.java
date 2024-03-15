package cn.gnaixeuy.sodamusic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

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
public class ResponseResult<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public static <T> ResponseResult<T> ok(T data) {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), data);
    }

    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), null);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
    }

    public static ResponseResult createInstance() {
        return new ResponseResult();
    }
}