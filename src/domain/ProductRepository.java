package domain;

import java.util.HashSet;
import java.util.Set;

public class ProductRepository {
    private static ProductRepository instance;
    private static final Set<Product> products = new HashSet<>();

    private ProductRepository() {
    }

    public static ProductRepository getInstance() {
        if(instance == null) {
            instance = new ProductRepository();
        }

        return instance;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void saveProduct(Product product) {
        products.add(product);
    }

    public void showProductList() {
        System.out.println("[상품목록]");
        for(Product product : products) {
            System.out.println(product);
        }
        System.out.println();
    }
}
