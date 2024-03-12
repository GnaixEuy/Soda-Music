package cn.gnaixeuy.sodamusic.service;

import cn.gnaixeuy.sodamusic.dto.music.MusicDto;
import cn.gnaixeuy.sodamusic.dto.music.MusicSaveRequest;

import java.util.List;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/11
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public interface MusicService {

    MusicDto getById(String id);

    List<MusicDto> getList();

    MusicDto saveOrUpdate(MusicSaveRequest musicSaveRequest);

    void deleteById(String id);

    void deleteByIds(List<String> ids);

}