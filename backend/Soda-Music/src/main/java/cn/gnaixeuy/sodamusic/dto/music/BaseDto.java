package cn.gnaixeuy.sodamusic.dto.music;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/12
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Data
public class BaseDto implements Serializable {
    private String id;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
}