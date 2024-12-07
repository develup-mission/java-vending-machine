package vendingmachine;

import vendingmachine.model.Cash;
import vendingmachine.model.Change;
import vendingmachine.model.ChangeCalculator;
import vendingmachine.model.VendingMachine;
import vendingmachine.response.CashResponse;
import vendingmachine.response.ChangeResponse;

public class VendingMachineService {

    private VendingMachine vendingMachine;

    public void setVendingMachine(VendingMachine vendingMachine) {
        this.vendingMachine =vendingMachine;
    }

    public ChangeResponse calcChange(int purchaseAmount, Cash inputAmount) {
        return vendingMachine.getChangeResponse(purchaseAmount, inputAmount);
    }

    public int getTotalSales() {
        return vendingMachine.getTotalSales();
    }

    public CashResponse getCashResponse() {
        return vendingMachine.getCashResponse();
    }
}
