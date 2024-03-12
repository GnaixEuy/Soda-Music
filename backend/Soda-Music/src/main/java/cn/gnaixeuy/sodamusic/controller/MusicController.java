package cn.gnaixeuy.sodamusic.controller;

import cn.gnaixeuy.sodamusic.dto.ResponseResult;
import cn.gnaixeuy.sodamusic.dto.music.MusicDto;
import cn.gnaixeuy.sodamusic.dto.music.MusicSaveRequest;
import cn.gnaixeuy.sodamusic.service.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/12
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@RestController
@RequestMapping(value = {"music"})
public class MusicController {

    private MusicService musicService;

    @GetMapping(value = {"/{id}"})
    public ResponseResult<MusicDto> getById(@PathVariable String id) {
        return ResponseResult.success(this.musicService.getById(id));
    }

    @GetMapping(value = {"/list"})
    public ResponseResult<List<MusicDto>> getList() {
        return ResponseResult.success(this.musicService.getList());
    }

    @PostMapping(value = {"/"})
    public ResponseResult<MusicDto> save(@RequestBody @Validated MusicSaveRequest musicSaveRequest) {
        return ResponseResult.success(this.musicService.saveOrUpdate(musicSaveRequest));
    }

    @DeleteMapping(value = {"/{id}"})
    public void deleteById(@PathVariable String id) {
        this.musicService.deleteById(id);
    }

    @DeleteMapping(value = {"/{ids}"})
    public void deleteById(@PathVariable List<String> ids) {
        this.musicService.deleteByIds(ids);
    }

    @Autowired
    public void setMusicService(MusicService musicService) {
        this.musicService = musicService;
    }
}