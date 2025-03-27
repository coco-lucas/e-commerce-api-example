package tech.buildrun.btgpactual.ex_api_atualizada.controller.dto;

import org.springframework.data.domain.Page;

//retorna todos os dados referentes a paginação
public record PaginationResponse(
                Integer page,
                Integer pageSize,
                Long totalElements,
                Integer totalPages) {

        public static PaginationResponse fromPage(Page<?> page) {
                return new PaginationResponse(
                                page.getNumber(),
                                page.getSize(),
                                page.getTotalElements(),
                                page.getTotalPages());
        }
}
