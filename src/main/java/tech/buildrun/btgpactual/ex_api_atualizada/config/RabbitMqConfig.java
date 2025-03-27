package tech.buildrun.btgpactual.ex_api_atualizada.config;

import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    // definir o nome da queue
    public static final String ORDER_CREATED_QUEUE = "btg-pactual-order-created";

    @Bean
    public Jackson2JsonMessageConverter jackson2JsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean // criar a fila
    public Declarable orderCreatedQueue(RabbitAdmin rabbitAdmin) {
        Queue queue = new Queue(ORDER_CREATED_QUEUE, true);
        rabbitAdmin.declareQueue(queue);
        return queue;
    }

}
