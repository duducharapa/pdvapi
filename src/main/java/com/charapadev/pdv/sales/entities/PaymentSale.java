package com.charapadev.pdv.sales.entities;

import com.charapadev.pdv.payments.entities.PaymentMethod;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Schema(description = "Intermediary class that represents each payment used to finish a sale")
@Entity
@Table(name="sale_payments")
public class PaymentSale {

    @Schema(description = "Payment on sale identifier", example = "8")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Sale sale;

    @ManyToOne
    private PaymentMethod method;

    @Schema(description = "Discriminated value paid on sale using the vinculated payment method", example = "30.25")
    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof PaymentSale that)) return false;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "PaymentSale{" +
                "id=" + id +
                ", sale=" + sale +
                ", method=" + method +
                ", amount=" + amount +
                '}';
    }

}
