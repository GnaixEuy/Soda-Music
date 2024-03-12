package cn.gnaixeuy.sodamusic.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/12
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicSaveRequest extends BaseDto {
    private String createdUser;
    private String updatedUser;
    private String fileId;
    private String musicName;
    private String musicDesc;
    private String artistId;
    private Instant musicReleaseTime;
    private String musicAlbumId;
    private Long musicPlayAmount;
    private String coverFileId;
}