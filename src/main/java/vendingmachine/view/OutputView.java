package vendingmachine.view;

import vendingmachine.response.CashResponse;
import vendingmachine.response.ChangeResponse;
import vendingmachine.response.ProductResponse;
import vendingmachine.response.SoldResponse;

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

    public void displayDone() {
        System.out.println("결제 완료! 감사합니다!");
    }

    public void displayChange(ChangeResponse response) {
        StringBuilder sb = new StringBuilder();
        int oneThousand = response.oneThousand();
        if (oneThousand > 0) {
            sb.append("1000 " + oneThousand + ", ");
        }
        int fiveHundred = response.fiveHundred();
        if (fiveHundred > 0) {
            sb.append("500 " + fiveHundred + ", ");
        }
        int oneHundred = response.oneHundred();
        if (oneHundred > 0) {
            sb.append("100 " + oneHundred + ", ");
        }
        int fifty = response.fifty();
        if (fifty > 0) {
            sb.append("50 " + fifty);
        }
        System.out.println("거스름 돈 " + sb);
    }

    public void displayAdminModeHeader() {
        System.out.println("관리자모드에 진입합니다.\n");
    }

    public void displayAdminMenu() {
        System.out.println("관리자 메뉴\n" +
                "1. 상품 추가 2. 통계 조회 3. 상품 삭제 4. 판매 시작\n");
    }

    public void displayAdminSold(List<SoldResponse> soldResponses) {
        soldResponses.forEach(sr -> {
            System.out.println(sr.id() + ". " + sr.name() + " : " + sr.sold() + "개");
        });
    }

    public void displayProduct(ProductResponse productResponse) {
        String name = productResponse.name();
        int price = productResponse.price();
        System.out.println(name + " 1개 " + price + "원");
    }

    public void displayTotalSales(int totalSales) {
        System.out.println("총 매출");
        System.out.println(NumberFormat.getInstance().format(totalSales) + "원");
        System.out.println();
    }

    public void displayCash(CashResponse cashResponse) {
        int oneThousand = cashResponse.oneThousand();
        int fiveHundred = cashResponse.fiveHundred();
        int oneHundred = cashResponse.oneHundred();
        int fifty = cashResponse.fifty();
        System.out.println("1000 - " + oneThousand);
        System.out.println("500 - " + fiveHundred);
        System.out.println("100 - " + oneHundred);
        System.out.println("50 - " + fifty);
    }
}
