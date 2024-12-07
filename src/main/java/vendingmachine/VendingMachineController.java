package vendingmachine;

import vendingmachine.model.*;
import vendingmachine.response.CashResponse;
import vendingmachine.response.ChangeResponse;
import vendingmachine.response.ProductResponse;
import vendingmachine.response.SoldResponse;
import vendingmachine.view.InputView;
import vendingmachine.view.OutputView;

import java.util.List;
import java.util.Random;

import static vendingmachine.model.Payment.*;

public class VendingMachineController {

    private final InputView inputView = new InputView();
    private final OutputView outputView = new OutputView();
    private final VendingMachineService vendingMachineService = new VendingMachineService();

    public void run() {
        System.out.println("판매 시작!");
        BeginningProduct.initProducts();
        Random random = new Random();
        Cash cash = Cash.of(random.nextInt(10), random.nextInt(10), random.nextInt(10), random.nextInt(10));
        VendingMachine vendingMachine = new VendingMachine(cash);
        vendingMachineService.setVendingMachine(vendingMachine);
        process();
    }

    private void process() {
        while (true) {
            displayProducts(Products.getProductResponses());
            processProduct();
        }
    }

    private void processProduct() {
        Product product = RetryExecutor.executeWithRetry(() -> {
            String productId = inputView.getProductId();
            return getProduct(productId);
        });
        if (product.isAdmin()) { // 관리자 모드
            processAdmin();
            return;
        }
        processPurchase(product);
    }

    private void processAdmin() {
        outputView.displayAdminModeHeader();
        outputView.displayAdminMenu();
        displayStatistics();

        adminLogic();
    }

    private void adminLogic() {
        while (true){
            outputView.displayAdminMenu();
            int input = RetryExecutor.executeWithRetry(this::getAdminInput);
            if (input == 1) { // 상품 추가
                adminAddProduct();
                continue;
            }
            if (input == 2) { // 통계 조회
                displayStatistics();
                continue;
            }
            if (input == 3) { // 상품 삭제
                removeProduct();
                continue;
            }
            return;
        }
    }

    private void adminAddProduct() {
        String[] split = RetryExecutor.executeWithRetry(this::getSplitAddProduct);
        String name = split[0];
        int price = StringUtils.parseInt(split[1]);
        int stock = StringUtils.parseInt(split[2]);
        int id = Products.getNewId();
        Product product = new Product(id, name, price, stock);
        Products.add(product);
    }

    private String[] getSplitAddProduct() {
        String rawAddProduct = inputView.getAddProduct();
        String[] split = rawAddProduct.split(",");
        if (split.length != 3) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
        return split;
    }

    private int getAdminInput() {
        int input = inputView.getAdminInput();
        if (input < 1 || input > 4) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
        return input;
    }

    private void removeProduct() {
        int id = inputView.getRemoveProductId();
        Products.removeById(id);
    }

    private void displayStatistics() {
        displaySolds();
        displayTotalSales();
        displayCash();
    }

    private void displayCash() {
        CashResponse cashResponse = vendingMachineService.getCashResponse();
        outputView.displayCash(cashResponse);
    }

    private void displayTotalSales() {
        int totalSales = vendingMachineService.getTotalSales();
        outputView.displayTotalSales(totalSales);
    }

    private void displaySolds() {
        List<SoldResponse> soldResponses = Products.getSoldResponses();
        outputView.displayAdminSold(soldResponses);
    }

    private void processPurchase(Product product) {
        ProductResponse productResponse = product.getProductResponse();
        outputView.displayProduct(productResponse);
        Payment payment = RetryExecutor.executeWithRetry(this::getPayment);

        if (payment == CREDIT_CARD || payment == TRANSPORTATION_CARD) {
            product.purchase();
            outputView.displayDone();
            return;
        }
        // 구입 금액 이상으로 현금을 투입한 경우 현금 투입 중단
        Cash inputAmount = RetryExecutor.executeWithRetry(() -> getInputAmount(product));
        ChangeResponse response;
        try {
            response = vendingMachineService.calcChange(product.getPrice(), inputAmount);
        } catch (IllegalArgumentException e) { // 거스름 돈이 부족한 경우
            System.out.println(e.getMessage());
            response = new ChangeResponse(
                    inputAmount.getOneThousand(),
                    inputAmount.getFiveHundred(),
                    inputAmount.getOneHundred(),
                    inputAmount.getFifty());
        }
        outputView.displayDone();
        outputView.displayChange(response);
        product.purchase();
    }

    private Cash getInputAmount(Product product) {
        int totalInputCash = 0;
        Cash inputAmount = Cash.of(0, 0, 0, 0);
        while (!product.isSufficientCashToBuy(totalInputCash)) {
            String cashAndCount = inputView.getCash(totalInputCash);
            String[] split = cashAndCount.split(" ");
            if (split.length != 2) {
                throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
            }
            int cash = StringUtils.parseInt(split[0]);
            int count = StringUtils.parseInt(split[1]);
            inputAmount.addAmount(cash, count);
            totalInputCash += cash * count;
        }
        return inputAmount;
    }

    private Payment getPayment() {
        int paymentId = inputView.getPaymentId();
        if (paymentId > values().length) {
            throw new IllegalArgumentException("잘못된 입력입니다. 다시 입력해주세요.");
        }
        return Payment.values()[paymentId - 1];
    }

    private Product getProduct(String productId) {
        if (productId.equals("12345")) {
            return new Product(12345, "admin", 0, 0);
        }
        Product product = Products.getById(productId);
        if (!product.isSufficient()) {
            throw new IllegalArgumentException("존재하지 않는 상품입니다. 다시 입력해주세요.");
        }
        return product;
    }

    private void displayProducts(List<ProductResponse> productResponses) {
        outputView.displayProducts(productResponses);
    }
}
