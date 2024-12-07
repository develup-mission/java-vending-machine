package vendingmachine;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import vendingmachine.model.Cash;
import vendingmachine.model.ChangeCalculator;
import vendingmachine.response.ChangeResponse;

public class ChangeCalculatorTest {

    // 거스름돈 테스트
    @Test
    void sufficientChangeTest() {
        // 1600원의 거스름 돈이 발생 하는 경우
        ChangeCalculator changeCalculator = new ChangeCalculator(); // 1000, 500, 100, 50
        changeCalculator.setCash(Cash.of(1, 1, 2, 2));
        ChangeResponse changeResponse =  changeCalculator.getChangeResponse(
                Cash.of(1, 0, 0, 0),
                Cash.of(2, 1, 1, 0));
        Assertions.assertEquals(1, changeResponse.oneThousand());
        Assertions.assertEquals(1, changeResponse.fiveHundred());
        Assertions.assertEquals(1, changeResponse.oneHundred());
        Assertions.assertEquals(0, changeResponse.fifty());
    }
}
