class Order {
    private final UUID orderId;
    private final List<OrderItem> orderItems;
    private final Customer customer;

    public Order(Customer customer) {
        this.orderId = UUID.randomUUID();
        this.orderItems = new ArrayList<>();
        this.customer = customer;
    }

    public boolean addItem(OrderItem orderItem) {
        if (orderItem == null) {
            return false;
        }
        for (OrderItem item : orderItems) {
            if (item.getArticle().equals(orderItem.getArticle())) {
                item.setQuantity(item.getQuantity() + orderItem.getQuantity());
                return true;
            }
        }
        orderItems.add(orderItem);
        return true;
    }

    public double getTotalPrice() {
        double total = 0.0;
        for (OrderItem item : orderItems) {
            total += item.getArticle().getPrice() * item.getQuantity();
        }
        return total;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public List<OrderItem> getOrderItems() {
        return new ArrayList<>(orderItems);
    }

    public Customer getCustomer() {
        return customer;
    }
}