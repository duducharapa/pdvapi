package com.charapadev.pdv.sales;

import com.charapadev.pdv.payments.PaymentMethodService;
import com.charapadev.pdv.payments.entities.PaymentMethod;
import com.charapadev.pdv.prices.PriceItemService;
import com.charapadev.pdv.prices.entities.PriceItem;
import com.charapadev.pdv.products.ProductService;
import com.charapadev.pdv.products.entities.Product;
import com.charapadev.pdv.sales.dtos.AddItemSale;
import com.charapadev.pdv.sales.dtos.AddPaymentMethod;
import com.charapadev.pdv.sales.dtos.AddSale;
import com.charapadev.pdv.sales.entities.ItemSale;
import com.charapadev.pdv.sales.entities.PaymentSale;
import com.charapadev.pdv.sales.entities.Sale;
import com.charapadev.pdv.sales.exceptions.SaleNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductService productService;
    private final PaymentMethodService paymentMethodService;
    private final PriceItemService  priceItemService;

    public SaleService(
            SaleRepository saleRepository,
            ProductService productService,
            PaymentMethodService paymentMethodService,
            PriceItemService priceItemService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.paymentMethodService = paymentMethodService;
        this.priceItemService = priceItemService;
    }

    // Checks if an product already exists by ID
    // It's used in operations where the ID is passed but the object itself is not necessary
    public boolean existsById(Long id) {
        return saleRepository.existsById(id);
    }


    // List all sales
    public List<Sale> findAll() {
        return saleRepository.findAllWithItems();
    }


    // Generates a new sale
    // First, the function add the items and later, the payments. At final, calculate the internal
    // variables inside Sale itself.
    @Transactional
    public void create(AddSale data) {
        Sale newSale = new Sale();
        Long tableId = data.priceTableId();
        newSale = saleRepository.save(newSale);

        // Items populating
        for (AddItemSale itemData: data.items()) {
            ItemSale item = new ItemSale();
            Long productId = itemData.productId();

            Product productFound = productService.findById(productId);
            PriceItem priceItem = priceItemService.findByProductAndTable(productId, tableId);

            item.setProduct(productFound);
            item.setSale(newSale);
            item.setFinalPrice(priceItem.getPrice());
            item.setQuantity(itemData.quantity());

            newSale.addItemSales(item);
        }

        // Payments populating
        for (AddPaymentMethod paymentData: data.payments()) {
            PaymentSale payment = new PaymentSale();
            PaymentMethod method = paymentMethodService.find(paymentData.methodId());

            payment.setAmount(paymentData.amount());
            payment.setMethod(method);
            payment.setSale(newSale);

            newSale.addPaymentSales(payment);
        }

        newSale.processTotals();
        saleRepository.save(newSale);
    }


    // Searches a product using the given ID
    public Sale findById(Long id) {
        return saleRepository.findByIdWithItems(id).orElseThrow(SaleNotFoundException::new);
    }


    // Makes a soft delete on sale identified by ID
    @Transactional
    public void delete(Long id) {
        saleRepository.markAsInactive(id);
    }

}
