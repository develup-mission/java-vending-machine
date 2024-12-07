package vendingmachine.model;


import java.util.Arrays;
import java.util.List;
import java.util.Random;

// 10. 콜라 - 1,350원
//11. 아이스 아메리카노 - 2,850원
//12. 오렌지 주스 - 2,500원
//13. 생수 - 950원
//14. 포테이토칩 - 1,800원
//15. 초코파이 - 1,200원
//16. 사탕 믹스 - 1,050원
//17. 월드콘 - 2,150원
//18. 메로나 - 1,200원
//19. 샌드위치 - 4,150원
public enum BeginningProduct {
    COLA(10, "콜라", 1350),
    ICE_AMERICANO(11, "아이스 아메리카노", 2850),
    ORANGE_JUICE(12, "오렌지 주스", 2500),
    WATER(13, "생수", 950),
    POTATO_CHIPS(14, "포테이토칩", 1800),
    CHOCO_PIE(15, "초코파이", 1200),
    CANDY_MIX(16, "사탕 믹스", 1050),
    WORLD_CON(17, "월드콘", 2150),
    MERONA(18, "메로나", 1200),
    SANDWICH(19, "샌드위치", 4150),
    ;

    private final int id;
    private final String name;
    private final int price;

    BeginningProduct(int id, String name, int price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public static void initProducts() {
        Random random = new Random();
        int randomStock = random.nextInt(10, 31);
        List<Product> beginningProducts = Arrays.stream(BeginningProduct.values())
                .map(ip -> new Product(ip.id, ip.name, ip.price, randomStock))
                .toList();
        Products.init(beginningProducts);
    }
}

