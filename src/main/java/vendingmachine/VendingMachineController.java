package vendingmachine;

import java.text.NumberFormat;
import java.util.List;

public class VendingMachineController {

    public void run() {
        Products products = BeginningProduct.initProducts();
        List<ProductResponse> productResponses = products.getProductResponses();
        productResponses.forEach(pr -> {
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
