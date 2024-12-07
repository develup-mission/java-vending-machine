package vendingmachine;

public record ChangeResponse(
        int oneThousand,
        int fiveHundred,
        int oneHundred,
        int fifty
) {
}
