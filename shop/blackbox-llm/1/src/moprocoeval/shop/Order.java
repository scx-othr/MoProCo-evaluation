public class Order {
    private final String orderId;
    private final List<OrderItem> orderItems;

    public Order() {
        this.orderId = UUID.randomUUID().toString();
        this.orderItems = new ArrayList<>();
    }

    public void addItem(OrderItem item) {
        OrderItem existingItem = orderItems.stream()
                .filter(i -> i.getArticle().equals(item.getArticle()))
                .findFirst()
                .orElse(null);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
        } else {
            orderItems.add(item);
        }
    }

    public BigDecimal totalPrice() {
        return orderItems.stream()
                .map(item -> item.getArticle().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }
}