package models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import pages.cart.CartProduct;

import java.math.BigDecimal;

@Data
public class Product {
    private String productName;
    private BigDecimal price;
    private int count;

    public Product(String productName, BigDecimal price, int count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }

    public Product(String productName, BigDecimal price, BigDecimal totalPrice) {
        this.productName = productName;
        this.price = price;
    }

    public Product(String productName, BigDecimal price) {
        this.productName = productName;
        this.price = price;
    }

    public Product(CartProduct cartProduct) {
        this.productName = cartProduct.getProductTitle();
        this.price = cartProduct.getProductPrice();
        this.count = cartProduct.getQuantity();
    }

}
