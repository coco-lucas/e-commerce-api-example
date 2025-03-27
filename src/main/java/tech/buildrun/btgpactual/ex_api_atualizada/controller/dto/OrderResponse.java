package tech.buildrun.btgpactual.ex_api_atualizada.controller.dto;

import java.math.BigDecimal;

import tech.buildrun.btgpactual.ex_api_atualizada.entities.OrderEntity;

public record OrderResponse(
        Long orderId,
        Long customerId,
        BigDecimal total) {

    public static OrderResponse fromEntity(OrderEntity entity) {// conversão
        return new OrderResponse(entity.getId(), entity.getCustomerId(), entity.getTotal());
    }
}
