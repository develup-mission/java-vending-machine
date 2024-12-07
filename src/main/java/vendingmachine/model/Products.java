package vendingmachine.model;

import vendingmachine.StringUtils;
import vendingmachine.response.ProductResponse;
import vendingmachine.response.SoldResponse;

import java.util.List;
import java.util.OptionalInt;

public class Products {

    private static List<Product> products;

    public static void init(List<Product> products) {
        Products.products = products;
    }

    public static Product getById(String productId) {
        return products.stream()
                .filter(p -> p.isSameId(StringUtils.parseInt(productId)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 입력입니다. 다시 입력하세요"));
    }

    public static List<SoldResponse> getSoldResponses() {
        return products.stream()
                .map(Product::getSoldResponse)
                .toList();
    }

    public static int getNewId() {
        OptionalInt max = products.stream()
                .mapToInt(Product::getId)
                .max();
        if (max.isPresent()) {
            return max.getAsInt() + 1;
        }
        return 10;
    }

    public static void add(Product product) {
        products.add(product);
    }

    public static void removeById(int id) {
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
