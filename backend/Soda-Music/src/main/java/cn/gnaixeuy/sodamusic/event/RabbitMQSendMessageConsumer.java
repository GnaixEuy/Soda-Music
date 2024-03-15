package cn.gnaixeuy.sodamusic.event;

import cn.gnaixeuy.sodamusic.dto.event.SendMessageTask;
import cn.gnaixeuy.sodamusic.enums.RabbitMQConstant;
import cn.gnaixeuy.sodamusic.service.MessageService;
import com.alibaba.fastjson2.JSONObject;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private MessageService messageService;

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
            this.messageService.sendPhoneCaptchaMessage(JSONObject.parseObject(new String(message.getBody()), SendMessageTask.class));
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            log.error("消息处理出现异常：{}", e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
        }
    }

    @Autowired
    @Qualifier(value = "tencent_message")
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

}