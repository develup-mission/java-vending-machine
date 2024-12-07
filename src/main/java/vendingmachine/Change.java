package vendingmachine;

public class Change {

    private int change;

    public Change(int change) {
        this.change = change;
    }

    public boolean canChangedBy(int cash) {
        return change <= cash;
    }

    public int getOneThousandChange() {
        int changeCount = change / 1000;
        change -= changeCount * 1000;
        return changeCount;
    }

    public int getFiveHundredChange() {
        int changeCount = change / 500;
        change -= changeCount * 500;
        return changeCount;
    }

    public int getOneHundredChange() {
        int changeCount = change / 100;
        change -= changeCount * 100;
        return changeCount;
    }

    public int getFiftyChange() {
        int changeCount = change / 50;
        change -= changeCount * 50;
        return changeCount;
    }
}
