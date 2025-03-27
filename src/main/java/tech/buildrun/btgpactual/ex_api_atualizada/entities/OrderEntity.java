package tech.buildrun.btgpactual.ex_api_atualizada.entities;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document(collection = "tb_orders")
public class OrderEntity {
    @MongoId
    private Long id;

    @Indexed(name = "customer_id_index") // para melhorar a performace ao fazer buscas
    private Long customerId;

    @Field(targetType = FieldType.DECIMAL128) // Mongo guarda BigDecimal como String, FieldType.DECIMAL128
                                              // faz guardar como Num

    private BigDecimal total;

    private List<OrderItem> items;

    public OrderEntity() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getTotal() {
        return this.total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<OrderItem> getItems() {
        return this.items;
    }

    public void setItems(List<OrderItem> items) {
        this.items = items;
    }
}
