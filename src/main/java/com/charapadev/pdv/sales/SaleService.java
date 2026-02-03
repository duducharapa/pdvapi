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

import java.math.BigDecimal;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ItemSaleRepository itemSaleRepository;
    private final PaymentSaleRepository paymentSaleRepository;
    private final ProductService productService;
    private final PaymentMethodService paymentMethodService;
    private final PriceItemService  priceItemService;

    public SaleService(
            SaleRepository saleRepository, ProductService productService, ItemSaleRepository itemSaleRepository,
            PaymentSaleRepository paymentSaleRepository, PaymentMethodService paymentMethodService,
            PriceItemService priceItemService) {
        this.saleRepository = saleRepository;
        this.productService = productService;
        this.itemSaleRepository = itemSaleRepository;
        this.paymentSaleRepository = paymentSaleRepository;
        this.paymentMethodService = paymentMethodService;
        this.priceItemService = priceItemService;
    }

    public boolean existsById(Long id) {
        return saleRepository.existsById(id);
    }

    public List<Sale> findAll() {
        return saleRepository.findAllWithItems();
    }

    @Transactional
    public void create(AddSale data) {
        Sale newSale = new Sale();
        Long tableId = data.priceTableId();
        newSale = saleRepository.save(newSale);

        for (AddItemSale itemData: data.items()) {
            ItemSale item = new ItemSale();
            Product productFound = productService.findById(itemData.productId());

            item.setProduct(productFound);
            item.setSale(newSale);
            item.setQuantity(itemData.quantity());
            itemSaleRepository.save(item);

            PriceItem priceItem = priceItemService.findByProductAndTable(productFound.getId(), tableId);
            newSale.increaseAmount(priceItem.getPrice().multiply(BigDecimal.valueOf(itemData.quantity())));
        }

        for (AddPaymentMethod paymentData: data.payments()) {
            PaymentSale payment = new PaymentSale();
            PaymentMethod method = paymentMethodService.findById(paymentData.methodId());

            payment.setAmount(paymentData.amount());
            payment.setMethod(method);
            payment.setSale(newSale);
            paymentSaleRepository.save(payment);
        }

        saleRepository.save(newSale);
    }

    public Sale findById(Long id) {
        return saleRepository.findByIdWithItems(id).orElseThrow(SaleNotFoundException::new);
    }

    @Transactional
    public void delete(Long id) {
        saleRepository.markAsInactive(id);
    }

}
