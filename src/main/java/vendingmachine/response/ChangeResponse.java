package vendingmachine.response;

public record ChangeResponse(
        int oneThousand,
        int fiveHundred,
        int oneHundred,
        int fifty
) {
}
