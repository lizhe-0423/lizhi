package com.lizhi.mq;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * BI 消息队列初始化
 * @author <a href="https://github.com/lizhe-0423">lizhi</a>
 */
public class BiInitMain {


    public static void main(String[] args) throws IOException, TimeoutException {

        ConnectionFactory factory=new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("lizhi");
        factory.setPassword("lizhe@0110266577");
        Connection connection=factory.newConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(MqConstant.BI_EXCHANGE_NAME,MqConstant.BI_EXCHANGE_TYPE);
        //创建队列分配队列名称
        channel.queueDeclare(MqConstant.BI_QUEUE_NAME,true,false,false,null);
        //交换机、队列进行绑定
        channel.queueBind(MqConstant.BI_QUEUE_NAME,MqConstant.BI_EXCHANGE_NAME,MqConstant.BI_ROUTING_KEY);

    }
}
