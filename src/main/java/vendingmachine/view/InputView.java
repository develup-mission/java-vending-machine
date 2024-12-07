package vendingmachine.view;

import vendingmachine.StringUtils;

import java.text.NumberFormat;
import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String getProductId() {
        System.out.println("상품을 골라주세요!");
        System.out.println(">");
        return scanner.nextLine();
    }

    public int getPaymentId() {
        System.out.println("결제 수단을 선택하세요!\n" +
                "1. 카드 결제 2. 교통 카드 결제 3. 현금 결제\n" +
                ">");
        int input = StringUtils.parseInt(scanner.nextLine());
        if (input < 1 || 3 < input) {
            throw new IllegalArgumentException("올바르지 않은 입력입니다. 다시 입력해주세요");
        }
        return input;
    }

    public String getCash(int nowAmount) {
        System.out.println("현금을 투입해주세요. 현재 투입된 금액은 " + NumberFormat.getInstance().format(nowAmount) + "원입니다.\n" +
                ">");
        return scanner.nextLine();
    }

    public int getAdminInput() {
        return StringUtils.parseInt(scanner.nextLine());
    }

    public String getAddProduct() {
        System.out.println("상품 정보를 입력하세요 (이름,가격,재고)\n" +
                ">");
        return scanner.nextLine();
    }

    public int getRemoveProductId() {
        System.out.println("삭제할 상품 번호를 입력하세요\n" +
                ">");
        return StringUtils.parseInt(scanner.nextLine());
    }
}
