package vendingmachine;

public class Product {

    private int id;
    private final String name;
    private final int price;
    private final int stock;

    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public ProductResponse getProductResponse() {
        return new ProductResponse(id, name, price, stock);
    }

    public boolean isSameId(int id) {
        return this.id == id;
    }

    public boolean isBiggerIdThan(int id) {
        return this.id > id;
    }

    public void decreaseId() {
        id -= 1;
    }
}
