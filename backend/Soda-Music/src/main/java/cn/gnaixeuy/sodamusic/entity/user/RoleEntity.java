package cn.gnaixeuy.sodamusic.entity.user;

import cn.gnaixeuy.sodamusic.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/2/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Getter
@Setter
@Entity
@Table(name = "role", schema = "soda-music")
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "id", nullable = false, length = 64))
})
public class RoleEntity extends BaseEntity {

    @Column(name = "name", length = 128)
    private String name;

    @Column(name = "title", length = 128)
    private String title;

}