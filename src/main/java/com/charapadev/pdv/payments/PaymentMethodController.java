package com.charapadev.pdv.payments;

import com.charapadev.pdv.payments.dtos.CreatePaymentMethod;
import com.charapadev.pdv.payments.dtos.UpdatePaymentMethod;
import com.charapadev.pdv.payments.entities.PaymentMethod;
import com.charapadev.pdv.payments.exceptions.PaymentNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Payment methods", description = "Manages the payment methods for transactions")
@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }


    @Operation(
            summary = "List payment methods",
            description = "Lists all payment method registered on application"
    )
    @ApiResponse(
            description = "Methods found successfully", responseCode = "200",
            content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = PaymentMethod.class))
            )
    )
    @GetMapping
    public List<PaymentMethod> findAll() {
        return paymentMethodService.findAll();
    }


    @Operation(
            summary = "Create payment method",
            description = "Generates a new payment method if does not exists any method with the same name"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201", description = "Payment method created successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentMethod.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "422", description = "Method with the given name already registered",
                    content = @Content()
            )
    })
    @PostMapping
    public PaymentMethod create(@RequestBody CreatePaymentMethod data) {
        return paymentMethodService.create(data);
    }


    @Operation(
            summary = "Search payment method",
            description = "Searches for a payment method using the given identifier and returns it if found",
            parameters = {
                    @Parameter(name = "id", description = "Method ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200", description = "Method found successfully",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = PaymentMethod.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404", description = "Method not found",
                    content = @Content()
            )
    })
    @GetMapping("/{id}")
    public PaymentMethod search(@PathVariable Long id) {
        PaymentMethod methodFound = paymentMethodService.findById(id);

        if (methodFound == null) throw new PaymentNotFoundException();

        return methodFound;
    }

    @Operation(
            summary = "Edit payment method",
            description = "Updates a method identified by ID using the given data provided on request body",
            parameters = {
                    @Parameter(name = "id", description = "Method ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "Method updated successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Method not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody UpdatePaymentMethod data) {
        boolean methodExists = paymentMethodService.existsById(id);

        if (!methodExists) throw new PaymentNotFoundException();

        paymentMethodService.update(id, data);
    }

    @Operation(
            summary = "Remove payment method",
            description = "Marks the given payment method identified by ID as inactive",
            parameters = {
                    @Parameter(name = "id", description = "Method ID", in = ParameterIn.PATH)
            }
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "204", description = "Method removed successfully",
                    content = @Content()
            ),
            @ApiResponse(
                    responseCode = "404", description = "Method not found",
                    content = @Content()
            )
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        boolean methodExists = paymentMethodService.existsById(id);

        if (!methodExists) throw new PaymentNotFoundException();

        paymentMethodService.delete(id);
    }

}
