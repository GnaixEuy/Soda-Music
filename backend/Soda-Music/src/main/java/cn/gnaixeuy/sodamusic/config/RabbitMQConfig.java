package cn.gnaixeuy.sodamusic.config;

import cn.gnaixeuy.sodamusic.enums.RabbitMQConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <img src="https://img1.baidu.com/it/u=2537966709,2852517020&fm=253&fmt=auto&app=138&f=JPEG?w=648&h=489"/> <br/>
 * Soda-Music
 * rabbitmq配置类：配置Exchange、Queue、以及绑定交换机
 *
 * @author GnaixEuy
 * @version 1.0
 * @date 2024/3/14
 * @see <a href="https://github.com/GnaixEuy">GnaixEuy</a>
 */
@EnableRabbit
@Configuration
public class RabbitMQConfig {

    private static final Logger log = LoggerFactory.getLogger(RabbitMQConfig.class);

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    /**
     * 比较常用的 Converter 就是 Jackson2JsonMessageConverter,在发送消息时，它会先将自定义的消息类序列化成json格式，
     * 再转成byte构造 Message，在接收消息时，会将接收到的 Message 再反序列化成自定义的类
     */
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        //SimpleRabbitListenerContainerFactory发现消息中有content_type有text就会默认将其转换成string类型的
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //开启手动ACK
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    /**
     * ReturnsCallback消息没有正确到达队列时触发回调，如果正确到达队列不执行
     * config : 需要开启rabbitmq发送失败回退
     * yml配置publisher-returns: true
     * 或rabbitTemplate.setMandatory(true);设置为true
     */
    @Bean
    public AmqpTemplate amqpTemplate() {
        rabbitTemplate.setEncoding("UTF-8");
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            String messageId = returnedMessage.getMessage().getMessageProperties().getMessageId();
            byte[] message = returnedMessage.getMessage().getBody();
            Integer replyCode = returnedMessage.getReplyCode();
            String replyText = returnedMessage.getReplyText();
            String exchange = returnedMessage.getExchange();
            String routingKey = returnedMessage.getRoutingKey();

            log.info("消息：{} 发送失败，消息ID：{} 应答码：{} 原因：{} 交换机：{} 路由键：{}",
                    new String(message), messageId, replyCode, replyText, exchange, routingKey);

        });
        return rabbitTemplate;
    }

    /**
     * 声明直连交换机  支持持久化
     */
    @Bean(RabbitMQConstant.DIRECT_EXCHANGE)
    public Exchange directExchange() {
        return ExchangeBuilder.directExchange(RabbitMQConstant.DIRECT_EXCHANGE).durable(true).build();
    }

    /**
     * 发送信息 消息队列
     */
    @Bean(RabbitMQConstant.SEND_MESSAGE)
    public Queue cancelOrderQueue() {
        return new Queue(RabbitMQConstant.SEND_MESSAGE, true, false, true);
    }

    /**
     * 把发送信息消息队列绑定到交换机上
     */
    @Bean
    public Binding cancelOrderBinding(@Qualifier(RabbitMQConstant.SEND_MESSAGE) Queue queue,
                                      @Qualifier(RabbitMQConstant.DIRECT_EXCHANGE) Exchange directExchange) {
        //RoutingKey :RabbitMQConstant.CANCEL_ORDER,这里设置与消息队列 同名
        return BindingBuilder.bind(queue).to(directExchange).with(RabbitMQConstant.SEND_MESSAGE).noargs();
    }

}