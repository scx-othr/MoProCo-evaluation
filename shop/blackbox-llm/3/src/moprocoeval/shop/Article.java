class Article {
    private final String ean;
    private final String name;
    private final BigDecimal price;
    private int stock;
    private final List<OrderItem> orderItems;

    public Article(String ean, String name, BigDecimal price) {
        if (!isValidEAN(ean)) {
            throw new IllegalArgumentException("Invalid EAN code");
        }
        this.ean = ean;
        this.name = name;
        this.price = price;
        this.stock = 0;
        this.orderItems = new ArrayList<>();
    }

    public String getEan() {
        return ean;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
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

    private boolean isValidEAN(String ean) {
        if (ean == null || ean.length() != 13) {
            return false;
        }
        try {
            for (int i = 0; i < ean.length(); i++) {
                if (!Character.isDigit(ean.charAt(i))) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}