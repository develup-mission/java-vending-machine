package vendingmachine;

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
}
