package cn.gnaixeuy.sodamusic.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-MusicEntity
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@RestController
@RequestMapping(value = {"/hello"})
public class HelloController {

    @GetMapping(value = {"/world"})
    public String helloWorld() {
        return "THE_START_OF_CONSTRUCTION_IS_GOOD";
    }

}