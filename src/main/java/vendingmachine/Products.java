package vendingmachine;

import java.util.List;

public class Products {

    private final List<Product> products;

    public Products(List<Product> products) {
        this.products = products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void removeById(int id) {
        Product product = products.stream()
                .filter(p -> p.isSameId(id))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다. 다시 입력하세요."));

        products.stream()
                .filter(p -> p.isBiggerIdThan(id))
                .forEach(Product::decreaseId);

        products.remove(product);
    }

    public List<ProductResponse> getProductResponses() {
        return products.stream()
                .map(Product::getProductResponse)
                .toList();
    }
}
