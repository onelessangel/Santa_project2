package entities;

import enums.Category;

public final class Gift {
    private final String productName;
    private final double price;
    private final Category category;

    public Gift(final String productName, final double price, final Category category) {
        this.productName = productName;
        this.price = price;
        this.category = category;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "\nGift{"
                + "productName='" + productName + '\''
                + ", price=" + price
                + ", category='" + category + '\''
                + '}' + '\n';
    }
}
