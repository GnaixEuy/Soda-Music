package cn.gnaixeuy.sodamusic.mapper.music;

import cn.gnaixeuy.sodamusic.dto.music.MusicDto;
import cn.gnaixeuy.sodamusic.dto.music.MusicSaveRequest;
import cn.gnaixeuy.sodamusic.entity.music.MusicEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface MusicMapper {
    MusicEntity toEntity(MusicDto musicDto);

    List<MusicEntity> toEntityList(List<MusicDto> musicDto);

    MusicDto toDto(MusicEntity musicEntity);

    List<MusicDto> toDtoList(List<MusicEntity> musicEntityList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MusicEntity partialUpdate(MusicDto musicDto, @MappingTarget MusicEntity musicEntity);

    MusicEntity toEntity(MusicSaveRequest musicSaveRequest);

}