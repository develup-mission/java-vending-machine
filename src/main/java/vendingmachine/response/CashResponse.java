package vendingmachine.response;

public record CashResponse(
        int oneThousand,
        int fiveHundred,
        int oneHundred,
        int fifty
) {
}
