class Article {
    private final String ean;
    private final String name;
    private final double price;
    private int stock;
    private final Shop shop;
    private final List<OrderItem> orderItems;

    public Article(String ean, String name, double price, Shop shop) {
        if (!isValidEan(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.shop = shop;
        this.orderItems = new ArrayList<>();
    }

    public boolean placeOrder(OrderItem orderItem) {
        if (orderItem == null || orderItem.getQuantity() <= 0 || stock < orderItem.getQuantity()) {
            return false;
        }
        stock -= orderItem.getQuantity();
        orderItems.add(orderItem);
        return true;
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Shop getShop() {
        return shop;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    private boolean isValidEan(String ean) {
        return ean != null && ean.matches("^\\d{13}$");
    }
}