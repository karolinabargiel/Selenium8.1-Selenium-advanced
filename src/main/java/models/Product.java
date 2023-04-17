package models;

import lombok.Data;
import pages.cart.CartProduct;
import java.math.BigDecimal;


@Data
public class Product implements Comparable<Product>{
    private String productName;
    private BigDecimal price;
    private int count;

    public Product(String productName, BigDecimal price, int count) {
        this.productName = productName;
        this.price = price;
        this.count = count;
    }



    public Product(CartProduct cartProduct) {
        this.productName = cartProduct.getProductTitle();
        this.price = cartProduct.getProductPrice();
        this.count = cartProduct.getQuantity();
    }

    @Override
    public int compareTo(Product other) {
        return 0;
    }
}
