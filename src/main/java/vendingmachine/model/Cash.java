package vendingmachine.model;

import vendingmachine.response.CashResponse;

public class Cash {

    private int oneThousand;
    private int fiveHundred;
    private int oneHundred;
    private int fifty;

    private Cash(int oneThousand, int fiveHundred, int oneHundred, int fifty) {
        this.oneThousand = oneThousand;
        this.fiveHundred = fiveHundred;
        this.oneHundred = oneHundred;
        this.fifty = fifty;
    }

    public static Cash of(int oneThousand, int fiveHundred, int oneHundred, int fifty) {
        return new Cash(oneThousand, fiveHundred, oneHundred, fifty);
    }

    public int calcTotal() {
        return oneThousand * 1000 + fiveHundred * 500 + oneHundred * 100 + fifty * 50;
    }

    public void addAmount(int cash, int count) {
        if (cash == 1000) {
            oneThousand += count;
            return;
        }
        if (cash == 500) {
            fiveHundred += count;
            return;
        }
        if (cash == 100) {
            oneHundred += count;
            return;
        }
        fifty += count;
    }

    public void use(int unit, int count) {
        if (unit == 1000) {
            oneThousand -= count;
            return;
        }
        if (unit == 500) {
            fiveHundred -= count;
            return;
        }
        if (unit == 100) {
            oneHundred -= count;
            return;
        }
        fifty += count;
    }

    public int getOneThousand() {
        return oneThousand;
    }

    public int getFiveHundred() {
        return fiveHundred;
    }

    public int getOneHundred() {
        return oneHundred;
    }

    public int getFifty() {
        return fifty;
    }

    public CashResponse getResponse() {
        return new CashResponse(oneThousand, fiveHundred, oneHundred, fifty);
    }
}
