package vendingmachine.response;

public record SoldResponse(
        int id,
        String name,
        int sold
) {
}
