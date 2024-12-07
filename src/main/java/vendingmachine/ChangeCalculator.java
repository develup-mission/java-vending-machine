package vendingmachine;

public class ChangeCalculator {

    private final Cash cash;

    public ChangeCalculator(Cash cash) {
        this.cash = cash;
    }

    public ChangeResponse getChangeResponse(Cash purchaseAmount, Cash inputAmount) {
        Change change = new Change(inputAmount.calcTotal() - purchaseAmount.calcTotal());
        checkCashOnHand(change);

        int oneThousandChange = change.getOneThousandChange();
        int fiveHundredChange = change.getFiveHundredChange();
        int oneHundredChange = change.getOneHundredChange();
        int fiftyChange = change.getFiftyChange();
        return new ChangeResponse(oneThousandChange, fiveHundredChange, oneHundredChange, fiftyChange);
    }

    private void checkCashOnHand(Change change) {
        if (!change.canChangedBy(cash.calcTotal())) {
            throw new IllegalArgumentException("죄송합니다. 현재 거스름돈을 지급할 수 없습니다. 결제를 취소합니다.");
        }
    }
}
