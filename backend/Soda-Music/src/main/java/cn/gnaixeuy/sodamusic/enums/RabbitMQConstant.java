package cn.gnaixeuy.sodamusic.enums;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/14
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
public interface RabbitMQConstant {

    /**
     * 交换机名称
     */
    String DIRECT_EXCHANGE = "directExchange";

    /**
     * 发送信息 队列名称 routing-key
     */
    String SEND_MESSAGE = "send-message";

}