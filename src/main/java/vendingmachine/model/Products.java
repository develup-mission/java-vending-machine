package vendingmachine.model;

import vendingmachine.ProductResponse;

import java.util.List;

public class Products {

    private static List<Product> products;

    public static void init(List<Product> products) {
        Products.products = products;
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

    public static List<ProductResponse> getProductResponses() {
        return products.stream()
                .map(Product::getProductResponse)
                .toList();
    }

    public static boolean isSufficient(String productId) {
        Product product = products.stream()
                .filter(p -> p.isSameId(Integer.parseInt(productId)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("해당하는 상품이 존재하지 않습니다"));
        return product.isSufficient();
    }
}
