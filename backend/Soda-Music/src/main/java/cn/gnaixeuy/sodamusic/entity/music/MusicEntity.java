package cn.gnaixeuy.sodamusic.entity.music;

import cn.gnaixeuy.sodamusic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "music", schema = "soda-music")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 64)),
        @AttributeOverride(name = "createdTime", column = @Column(name = "created_time")),
        @AttributeOverride(name = "updatedTime", column = @Column(name = "updated_time"))
})
public class MusicEntity extends BaseEntity {
    @Column(name = "created_user", length = 64)
    private String createdUser;

    @Column(name = "updated_user", length = 64)
    private String updatedUser;

    @Column(name = "file_id", length = 64)
    private String fileId;

    @Column(name = "music_name", length = 64)
    private String musicName;

    @Column(name = "music_desc")
    private String musicDesc;

    @Column(name = "artist_id", length = 64)
    private String artistId;

    @Column(name = "music_release_time")
    private Instant musicReleaseTime;

    @Column(name = "music_album_id", length = 64)
    private String musicAlbumId;

    @Column(name = "music_play_amount")
    private Long musicPlayAmount;

    @Column(name = "cover_file_id", length = 64)
    private String coverFileId;

}