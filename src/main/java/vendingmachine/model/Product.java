package vendingmachine.model;

import vendingmachine.response.ProductResponse;
import vendingmachine.response.SoldResponse;

public class Product {

    private int id;
    private final String name;
    private final int price;
    private int stock;
    private int sold = 0;

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

    public boolean isSufficient() {
        return stock > 0;
    }

    public boolean isSufficientCashToBuy(int cash) {
        return price <= cash;
    }

    public int getPrice() {
        return price;
    }

    public SoldResponse getSoldResponse() {
        return new SoldResponse(id, name, sold);
    }

    public void purchase() {
        this.stock -= 1;
        this.sold += 1;
    }

    public boolean isAdmin() {
        return name.equals("admin");
    }

    public int getId() {
        return id;
    }
}
