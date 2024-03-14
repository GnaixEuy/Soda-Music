package cn.gnaixeuy.sodamusic.utils;

import cn.gnaixeuy.sodamusic.enums.RabbitMQConstant;
import com.alibaba.fastjson2.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/14
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@Component
public class DirectSender {

    private static final Logger log = LoggerFactory.getLogger(DirectSender.class);

    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     */
    public void send(String routingKey, Object msgData) {
        Message message = MessageBuilder.withBody(JSONObject.toJSONString(msgData).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID() + "").build();

        log.info("【发送者】消息内容【{}】 交换机【{}】 路由【{}】 消息ID【{}】", msgData, RabbitMQConstant.DIRECT_EXCHANGE
                , routingKey, message.getMessageProperties().getMessageId());
        rabbitTemplate.convertAndSend(RabbitMQConstant.DIRECT_EXCHANGE, routingKey, message);
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

}