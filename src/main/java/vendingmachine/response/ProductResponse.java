package vendingmachine.response;

public record ProductResponse(
        int id,
        String name,
        int price,
        int stock
) {
}
