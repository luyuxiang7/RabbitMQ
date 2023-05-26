package cn.itcast.mq.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalTime;


@Component
public class SpringRabbitListener {

    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueueMessage(String msg) throws InterruptedException {
        System.out.println("spring 消费者接收到消息：【" + msg + "】");
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue1(Message message) throws InterruptedException {
        System.out.println("消费者1接收到消息：【" + message + "】" + LocalTime.now());
    }


    /*    @RabbitListener(bindings = @QueueBinding(
                value = @Queue(name = "fanout.quque1"),
                exchange = @Exchange(name = "itcast.fanout",
                        type = ExchangeTypes.FANOUT )
                ,key = {}
        )*/
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到Fanout消息：【" + msg + "】");
    }

/*        @RabbitListener(bindings = @QueueBinding(
                value = @Queue(name = "fanout.quque2"),
                exchange = @Exchange(name = "itcast.fanout6",type = ExchangeTypes.FANOUT)
                ,key = {}
        )
        )*/
    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到Fanout消息：【" + msg + "】");
    }
}