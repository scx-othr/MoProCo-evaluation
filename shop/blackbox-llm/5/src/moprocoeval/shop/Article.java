class Article {
    private final String ean;
    private final String name;
    private final double price;
    private int stock;
    private final List<OrderItem> orderItems;
    private final Shop shop;

    public Article(String ean, String name, double price, Shop shop) throws IllegalArgumentException {
        if (!isValidEan(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.orderItems = new ArrayList<>();
        this.shop = shop;
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

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public Shop getShop() {
        return shop;
    }

    private boolean isValidEan(String ean) {
        return ean != null && ean.length() == 13 && ean.matches("\\d+");
    }
}