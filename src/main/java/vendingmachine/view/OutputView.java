package vendingmachine.view;

import vendingmachine.ProductResponse;

import java.text.NumberFormat;
import java.util.List;

public class OutputView {

    public void displayProducts(List<ProductResponse> responses) {
        System.out.println("상품 목록");
        responses.forEach(pr -> {
            int id = pr.id();
            String name = pr.name();
            int price = pr.price();
            int stock = pr.stock();
            if (stock > 0) {
                System.out.println(id + ". " + name + " - " + NumberFormat.getInstance().format(price) + "원");
            }
        });
    }
}
