package tech.buildrun.btgpactual.ex_api_atualizada.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import tech.buildrun.btgpactual.ex_api_atualizada.config.RabbitMqConfig;
import tech.buildrun.btgpactual.ex_api_atualizada.listener.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.ex_api_atualizada.service.OrderService;

@Component
public class OrderCreatedListener {
    // para consumir a fila do rabbitMQ

    private final Logger logger = LoggerFactory.getLogger(OrderCreatedListener.class);
    private final OrderService orderService;

    public OrderCreatedListener(OrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = RabbitMqConfig.ORDER_CREATED_QUEUE)
    public void listen(Message<OrderCreatedEvent> message) {
        logger.info("Message consumed: {}", message);

        orderService.save(message.getPayload());// pega todo o log criado e envia para o service
    }
}
