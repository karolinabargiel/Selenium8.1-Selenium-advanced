package models;

import lombok.Data;

import java.math.BigDecimal;
import java.util.*;


@Data
public class Cart {
    private List<Product> products = new ArrayList<>();
    private BigDecimal totalOrderCost = BigDecimal.valueOf(0);

    public Cart() {
    }

    public Product getProduct(String productName) {
        for (Product product : products) {
            if (Objects.equals(product.getProductName(), productName)) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (products.size() == 0 || products.stream().anyMatch(prod -> !Objects.equals(prod.getProductName(), product.getProductName()))) {
            products.add(product);
            increaseTotalOrderCost(product);
            return;
        }

        for (Product product1 : products) {
            if (Objects.equals(product1.getProductName(), product1.getProductName())) {
                product1.setCount(product1.getCount() + 1);
                break;
            }
        }
        increaseTotalOrderCost(product);
    }

    private void increaseTotalOrderCost(Product product) {
        totalOrderCost = totalOrderCost.add(BigDecimal.valueOf(product.getCount()).multiply(product.getPrice()));
    }

    public BigDecimal getTotalOrderCost() {
        return totalOrderCost;
    }

    public BigDecimal getTotalOrderCostWithShipping() {
        return totalOrderCost.add(BigDecimal.valueOf(7));
    }

    public static Cart getUniqueProducts(Cart expectedCart) {
        Map<String, Product> productMap = new HashMap<>();
        for (Product product : expectedCart.getProducts()) {
            String productName = product.getProductName();
            if (productMap.containsKey(productName)) {
                Product existingProduct = productMap.get(productName);
                existingProduct.setCount(existingProduct.getCount() + product.getCount());
            } else {
                productMap.put(productName, new Product(productName, product.getPrice(), product.getCount()));
            }
        }
        List<Product> uniqueProducts = new ArrayList<>(productMap.values());
        BigDecimal totalOrderCost = expectedCart.getTotalOrderCost();
        Cart cartWithUniqueProducts = new Cart();
        cartWithUniqueProducts.setProducts(uniqueProducts);
        cartWithUniqueProducts.setTotalOrderCost(totalOrderCost);
        return cartWithUniqueProducts;
    }

    public static List<Product> getListOfProductsExpCart(Cart expectedCart) {
        return getUniqueProducts(expectedCart).getProducts();
    }

    public Cart checkDeletedProducts(String productName) {
        for (Iterator<Product> iter = products.iterator(); iter.hasNext(); ) {
            Product product = iter.next();
            if (product.getProductName().equals(productName)) {
                iter.remove();
                totalOrderCost = totalOrderCost.subtract(product.getPrice());
                return this;
            }
        }
        return null;
    }


}
