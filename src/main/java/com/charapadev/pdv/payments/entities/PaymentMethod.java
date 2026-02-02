package com.charapadev.pdv.payments.entities;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

@Schema(description = "Manages the payment methods for transactions")
@Entity
@Table(name = "payment-method")
public class PaymentMethod {

    @Schema(description = "Payment identifier", example = "5")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(
            description = "Unique name used to identify the instance on reports and sales closing",
            example = "Crédito de loja"
    )
    @Column(unique = true, nullable = false)
    private String name;

    @Schema(
            description = "Boolean value that shows if the payment method is available to be used on transactions",
            example = "false"
    )
    @Column(nullable = false)
    private Boolean active = true;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
