package com.lizhi.mq;
import cn.dev33.satoken.stp.StpUtil;
import com.lizhi.common.BusinessException;
import com.lizhi.common.ErrorCode;
import com.lizhi.manage.AiManage;
import com.lizhi.service.BiChartService;
import com.lizhicommen.entity.BiChart;
import com.lizhicommen.entity.Users;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.io.IOException;
/**
 * @author <a href="https://github.com/lizhe-0423">lizhi</a>
 */
@Component
@Slf4j
public class BiMessageConsumer {

    @Resource
    private BiChartService chartService;
    @Resource
    private AiManage aiManage;

    /**
     *@description  指定程序监听的消息队列和确认机制
     *@param message 消息
     *@param channel 队列
     *@author <a href="https://github.com/lizhe-0423">荔枝程序员</a>
     **/
    @RabbitListener(queues = {MqConstant.BI_QUEUE_NAME},ackMode = "MANUAL")
    public void receiveMessage(String message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {

        if(StringUtils.isBlank(message)){
            // 拒绝并且不放到消息队列中
            channel.basicNack(deliveryTag,false,false);
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"消息为空");
        }
        Long chartId= Long.parseLong(message);
        BiChart chart = chartService.getById(chartId);
        if(chart==null){
            channel.basicNack(deliveryTag,false,false);
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"图表为空");
        }
        BiChart updateChartBefore = new BiChart();
        updateChartBefore.setChartId(chart.getChartId());
        updateChartBefore.setChartStatus("running");
        boolean b = chartService.updateById(updateChartBefore);
        if (!b) {
            chartService.handleChartUpdateError(updateChartBefore, "更新图表执行状态失败");
            return;
        }
        // 获取登录用户的 ak、sk
        Users loginUser = (Users) StpUtil.getSession().get("user");
        String userAccessKey = loginUser.getAccessKey();
        String userSecretKey = loginUser.getSecretKey();
        //调用AI
        String result = aiManage.doChat(MqConstant.BI_MODEL_ID, chartService.getChartMessage(chart.getChartGoal(),chart.getChartType(),chart.getChartText()), userAccessKey, userSecretKey);
        //将Ai的结果进行分割、更新处理
        BiChart updateChartAfter = chartService.receiveChartMessage(result, chart);
        boolean b2 = chartService.updateById(updateChartAfter);
        if (!b2) {
            channel.basicNack(deliveryTag,false,false);
            chartService.handleChartUpdateError(updateChartAfter, "更新图表成功状态失败");
        }
        log.info("receiveMessage message={}",message);
        //手动执行ack 消息确认
        channel.basicAck(deliveryTag,false);
    }


}
