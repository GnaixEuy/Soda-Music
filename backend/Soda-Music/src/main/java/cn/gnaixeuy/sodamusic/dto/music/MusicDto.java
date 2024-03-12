package cn.gnaixeuy.sodamusic.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.Instant;

/**
 * DTO for {@link cn.gnaixeuy.sodamusic.entity.music.MusicEntity}
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MusicDto extends BaseDto {
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