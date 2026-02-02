class Order {
    private final String orderId;
    private final List<OrderItem> orderItems;

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        for (OrderItem existingItem : orderItems) {
            if (existingItem.getArticle().equals(item.getArticle())) {
                existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
                return;
            }
        }
        orderItems.add(item);
    }

    public BigDecimal totalPrice() {
        BigDecimal total = BigDecimal.ZERO;
        for (OrderItem item : orderItems) {
            total = total.add(item.getArticle().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        return total;
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }
}