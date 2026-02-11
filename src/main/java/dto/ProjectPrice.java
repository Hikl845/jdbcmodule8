package dto;

public class ProjectPrice {
    private String name;
    private int price;

    public ProjectPrice(String name, int price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return name + " -> " + price;
    }
}
