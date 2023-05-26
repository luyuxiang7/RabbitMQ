package cn.itcast.mq.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringAmqpTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * testSimpleQueue : 基本消息队列
     */
    @Test
    public void testSimpleQueue() {
        // 队列名称
        String queueName = "simple.queue";
        // 消息
        String message = "java之父 - 柳文迪";
        // 发送消息
        rabbitTemplate.convertAndSend(queueName, message);
    }

    /**
     * testWorkQueue : 工作消息队列
     */
    @Test
    public void testWorkQueue() throws InterruptedException {
        // 队列名称
        String queueName = "work.queue";
        // 消息
        String message = "java之父 - 柳文迪";
        //多次发送
        for (int i = 0; i < 100; i++) {
            rabbitTemplate.convertAndSend(queueName, message + (i + 1));
        }
    }

    @Test
    public void testFanoutExchange() {
        // 队列名称
        String exchangeName = "itcast.fanout";
        // 消息
        String message = "hello, everyone!";
        rabbitTemplate.convertAndSend(exchangeName, "", message);
    }
}