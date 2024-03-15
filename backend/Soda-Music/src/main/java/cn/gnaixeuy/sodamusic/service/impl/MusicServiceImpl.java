package cn.gnaixeuy.sodamusic.service.impl;

import cn.gnaixeuy.sodamusic.dto.music.MusicDto;
import cn.gnaixeuy.sodamusic.dto.music.MusicSaveRequest;
import cn.gnaixeuy.sodamusic.entity.music.MusicEntity;
import cn.gnaixeuy.sodamusic.enums.CacheNameConstant;
import cn.gnaixeuy.sodamusic.enums.ExceptionType;
import cn.gnaixeuy.sodamusic.exception.BizException;
import cn.gnaixeuy.sodamusic.mapper.music.MusicMapper;
import cn.gnaixeuy.sodamusic.repository.MusicRepository;
import cn.gnaixeuy.sodamusic.service.MusicService;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/12
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Service
public class MusicServiceImpl implements MusicService {
    private MusicRepository musicRepository;
    private MusicMapper musicMapper;

    @Override
    @Cacheable(cacheNames = {CacheNameConstant.MUSIC,}, key = "#id")
    public MusicDto getById(String id) {
        Optional<MusicEntity> musicOptional = this.musicRepository.findById(id);
        if (musicOptional.isEmpty()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        return this.musicMapper.toDto(musicOptional.get());
    }

    @Override
    public List<MusicDto> getList() {
        return this.musicMapper.toDtoList(this.musicRepository.findAll());
    }

    @Override
    @CacheEvict(cacheNames = {CacheNameConstant.MUSIC,}, key = "#musicSaveRequest.id", condition = "!musicSaveRequest.id.empty")
    public MusicDto saveOrUpdate(MusicSaveRequest musicSaveRequest) {
        MusicEntity musicEntity;
        String musicSaveRequestId = musicSaveRequest.getId();
        if (StrUtil.isNotBlank(musicSaveRequestId)) {
            Optional<MusicEntity> musicOptional = this.musicRepository.findById(musicSaveRequestId);
            if (musicOptional.isEmpty()) {
                throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
            }
            MusicDto musicDto = this.musicMapper.toDto(musicOptional.get());
            musicEntity = this.musicRepository.save(
                    this.musicMapper.partialUpdate(musicDto, new MusicEntity()));
        } else {
            musicEntity = this.musicRepository.save(this.musicMapper.toEntity(musicSaveRequest));
        }
        return this.musicMapper.toDto(musicEntity);
    }

    @Override
    public void deleteById(String id) {
        if (this.musicRepository.findById(id).isEmpty()) {
            throw new BizException(ExceptionType.MUSIC_NOT_FOUND);
        }
        this.musicRepository.deleteById(id);
    }

    @Override
    public void deleteByIds(List<String> ids) {
        if (CollUtil.isEmpty(ids)) {
            return;
        }
        this.musicRepository.deleteAllByIdInBatch(ids);
    }

    @Autowired
    public void setMusicMapper(MusicMapper musicMapper) {
        this.musicMapper = musicMapper;
    }

    @Autowired
    public void setMusicRepository(MusicRepository musicRepository) {
        this.musicRepository = musicRepository;
    }

}