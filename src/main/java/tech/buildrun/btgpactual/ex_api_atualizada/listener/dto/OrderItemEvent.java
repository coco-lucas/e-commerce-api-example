package tech.buildrun.btgpactual.ex_api_atualizada.listener.dto;

import java.math.BigDecimal;

public record OrderItemEvent(
                String produto,
                Integer quantidade,
                BigDecimal preco) {

}
