package vendingmachine;

import vendingmachine.model.BeginningProduct;
import vendingmachine.model.Products;
import vendingmachine.view.OutputView;

import java.util.List;
import java.util.Scanner;

public class VendingMachineController {

    private final OutputView outputView = new OutputView();
    private final Scanner scanner = new Scanner(System.in);

    public void run() {
        System.out.println("판매 시작!");
        BeginningProduct.initProducts();
        while (true) {
            displayProducts(Products.getProductResponses());
            System.out.println("상품을 골라주세요!");
            System.out.println(">");
            String productId = scanner.nextLine();
            processProduct(productId);
        }
    }

    private void processProduct(String productId) {
        if (productId.equals("12345")) { // 관리자 모드
            return;
        }
        if (Products.isSufficient(productId)) { // 상품이 충분한 경우
            
            return;
        }
        // 상품이 충분하지 않은 경우
        throw new IllegalArgumentException("상품의 수가 부족합니다.");
    }

    private void displayProducts(List<ProductResponse> productResponses) {
        outputView.displayProducts(productResponses);
    }
}
