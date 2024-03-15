package cn.gnaixeuy.sodamusic.config.tencent;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/15
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Configuration
@ConfigurationProperties(prefix = "tencent.cos")
public class TencentCosConfig {

    private String appId;
    private String secretId;
    private String secretKey;
    private String bucketName;
    private String regionId;
    private String baseUrl;

}