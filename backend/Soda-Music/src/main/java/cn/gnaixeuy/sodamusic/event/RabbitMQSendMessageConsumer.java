package cn.gnaixeuy.sodamusic.event;

import cn.gnaixeuy.sodamusic.enums.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/14
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Slf4j
@Component
public class RabbitMQSendMessageConsumer {

    /**
     * 接受消息
     */
    @RabbitHandler
    @RabbitListener(queues = RabbitMQConstant.SEND_MESSAGE)
    public void receiverMsg(Channel channel, Message message) throws Exception {
        //body 即消息体
        String msg = new String(message.getBody());
        String messageId = message.getMessageProperties().getMessageId();
        log.info("【消费者】 消息内容：【{}】。messageId 【{}】", msg, messageId);

        try {
            //如果有业务逻辑，则在这里编写


            //告诉服务器收到这条消息 无需再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("消息处理出现异常：{}", e.getMessage());
            //告诉消息服务器 消息处理异常，消息需要重新再次发送！
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }
}