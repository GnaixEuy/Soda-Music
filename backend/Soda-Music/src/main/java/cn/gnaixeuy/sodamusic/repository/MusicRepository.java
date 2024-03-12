package cn.gnaixeuy.sodamusic.repository;

import cn.gnaixeuy.sodamusic.entity.music.MusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/11
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Repository
public interface MusicRepository extends JpaRepository<MusicEntity, String> {
    @Override
    Optional<MusicEntity> findById(String s);

    List<MusicEntity> findByIdIn(List<String> ids);

}