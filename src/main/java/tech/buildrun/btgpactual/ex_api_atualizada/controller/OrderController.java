package tech.buildrun.btgpactual.ex_api_atualizada.controller;

import org.springframework.web.bind.annotation.RestController;

import tech.buildrun.btgpactual.ex_api_atualizada.controller.dto.ApiResponse;
import tech.buildrun.btgpactual.ex_api_atualizada.controller.dto.OrderResponse;
import tech.buildrun.btgpactual.ex_api_atualizada.controller.dto.PaginationResponse;
import tech.buildrun.btgpactual.ex_api_atualizada.service.OrderService;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/customers/{customerId}/orders") // retorna sempre pedidos referentes a clientes
    public ResponseEntity<ApiResponse<OrderResponse>> listOrders(
            @PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {// fazer isto para paginação

        Page<OrderResponse> pageResponse = orderService.findAllByCustomerId(customerId, PageRequest.of(page, pageSize));

        var totalOnOrders = orderService.findTotalOnOrdersByCustomerId(customerId);

        return ResponseEntity.ok(new ApiResponse<>(
                Map.of("totalOnOrders", totalOnOrders),
                pageResponse.getContent(),
                PaginationResponse.fromPage(pageResponse)));
    }

}
