package vendingmachine.model;

import vendingmachine.response.CashResponse;
import vendingmachine.response.ChangeResponse;

public class ChangeCalculator {

    private final Cash cash; // 자판기가 보유 중인 현금

    public ChangeCalculator(Cash cash) {
        this.cash = cash;
    }

    public CashResponse getCashResponse() {
        return cash.getResponse();
    }

    public ChangeResponse getChangeResponse(int purchaseAmount, Cash inputAmount) {
        Change change = new Change(inputAmount.calcTotal() - purchaseAmount);
        checkCashOnHand(change);

        int oneThousandChange = change.getOneThousandChange();
        int fiveHundredChange = change.getFiveHundredChange();
        int oneHundredChange = change.getOneHundredChange();
        int fiftyChange = change.getFiftyChange();

        cash.use(1000, oneThousandChange);
        cash.use(500, fiveHundredChange);
        cash.use(100, oneHundredChange);
        cash.use(50, fiftyChange);
        return new ChangeResponse(oneThousandChange, fiveHundredChange, oneHundredChange, fiftyChange);
    }

    private void checkCashOnHand(Change change) {
        if (!change.canChangedBy(cash.calcTotal())) {
            throw new IllegalArgumentException("죄송합니다. 현재 거스름돈을 지급할 수 없습니다. 결제를 취소합니다.");
        }
    }
}
