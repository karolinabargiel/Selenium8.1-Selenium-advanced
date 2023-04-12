package models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
public class Cart {
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalOrderCost = BigDecimal.valueOf(0);

    public Cart(List<Product> products, BigDecimal totalOrderCost) {
        this.products = products;
        this.totalOrderCost = totalOrderCost;
    }

    public Cart() {
    }

    public Product getProduct(String productName){
        return products.stream().filter(prod -> Objects.equals(prod.getProductName(), productName)).findFirst().orElse(null);
    }
    public void addProduct(Product product) {
        if (products.size() == 0 || products.stream().anyMatch(prod -> !Objects.equals(prod.getProductName(), product.getProductName()))){
            products.add(product);
            increaseTotalOrderCost(product);
            return;
        }

        products.stream().filter(prod -> Objects.equals(prod.getProductName(), product.getProductName())).findFirst().ifPresent(prod -> prod.setCount(prod.getCount() + 1));
        increaseTotalOrderCost(product);
    }

    private void increaseTotalOrderCost(Product product) {
        totalOrderCost = totalOrderCost.add(BigDecimal.valueOf(product.getCount()).multiply(product.getPrice()));
    }
}
