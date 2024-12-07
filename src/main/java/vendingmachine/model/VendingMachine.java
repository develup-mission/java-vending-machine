package vendingmachine.model;

import vendingmachine.response.CashResponse;
import vendingmachine.response.ChangeResponse;

public class VendingMachine {

    private final ChangeCalculator changeCalculator;
    private int totalSales = 0;

    public VendingMachine(Cash cash) {
        changeCalculator = new ChangeCalculator(cash);
    }

    public ChangeResponse getChangeResponse(int purchaseAmount, Cash inputAmount) {
        ChangeResponse changeResponse = changeCalculator.getChangeResponse(purchaseAmount, inputAmount);
        totalSales += purchaseAmount;
        return changeResponse;
    }

    public int getTotalSales() {
        return totalSales;
    }

    public CashResponse getCashResponse() {
        return changeCalculator.getCashResponse();
    }
}
