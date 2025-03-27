package tech.buildrun.btgpactual.ex_api_atualizada.service;

import java.math.BigDecimal;
import java.util.List;

import org.bson.Document;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.AggregationPipeline;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.stereotype.Service;

import com.mongodb.internal.operation.AggregateOperation;

import tech.buildrun.btgpactual.ex_api_atualizada.controller.dto.OrderResponse;
import tech.buildrun.btgpactual.ex_api_atualizada.entities.OrderEntity;
import tech.buildrun.btgpactual.ex_api_atualizada.entities.OrderItem;
import tech.buildrun.btgpactual.ex_api_atualizada.listener.dto.OrderCreatedEvent;
import tech.buildrun.btgpactual.ex_api_atualizada.listener.repository.OrderRepository;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final MongoTemplate mongoTemplate; // permite querys mais complexas, como agregation e agrupamento

    public OrderService(OrderRepository orderRepository, MongoTemplate mongoTemplate) {
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    public void save(OrderCreatedEvent event) {
        OrderEntity entity = new OrderEntity();
        entity.setId(event.codigoPedido());
        entity.setCustomerId(event.codigoCliente());
        // entity.setTotal(null);
        entity.setItems(getOrderItems(event));
        entity.setTotal(getTotal(event));

        orderRepository.save(entity);
    }

    public Page<OrderResponse> findAllByCustomerId(Long customerId, PageRequest pageRequest) {
        Page<OrderEntity> orders = orderRepository.findAllByCustomerId(customerId, pageRequest);
        return orders.map(OrderResponse::fromEntity);
    }

    private static List<OrderItem> getOrderItems(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> new OrderItem(i.produto(), i.quantidade(), i.preco()))
                .toList(); // estamos pegando os itens que vem do evento, convertendo para OrderItem e
                           // botando em lista
    }

    public BigDecimal findTotalOnOrdersByCustomerId(Long customerId) {
        var aggregation = newAggregation(
                match(Criteria.where("customerId").is(customerId)),
                group().sum("total").as("total"));

        var response = mongoTemplate.aggregate(aggregation, "tb_orders", Document.class);

        return new BigDecimal(response.getUniqueMappedResult().get("total").toString());
    }

    private static BigDecimal getTotal(OrderCreatedEvent event) {
        return event.itens()
                .stream()
                .map(i -> i.preco().multiply(BigDecimal.valueOf(i.quantidade())))// multiplica o preço pela quantidade
                .reduce(BigDecimal::add)// soma tudo no resultado final
                .orElse(BigDecimal.ZERO); // se não tiver nada, retorna 0
    }

}
