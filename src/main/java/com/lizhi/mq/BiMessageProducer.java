package com.lizhi.mq;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

/**
 *
 * @author <a href="https://github.com/lizhe-0423">lizhi</a>
 */
@Component
public class BiMessageProducer {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param message 消息
     */
    public void sendMessage(String message){
        rabbitTemplate.convertAndSend(MqConstant.BI_EXCHANGE_NAME,MqConstant.BI_ROUTING_KEY,message);
    }
}
