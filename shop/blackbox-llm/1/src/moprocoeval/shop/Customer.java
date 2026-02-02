public class Customer {
    private final String name;
    private final String email;
    private final List<Order> orders;

    public Customer(String name, String email) {
        if (!isValidEmail(email)) {
            throw new IllegalArgumentException("Invalid email address");
        }
        this.name = name;
        this.email = email;
        this.orders = new ArrayList<>();
    }

    public boolean placeOrder(Order order) {
        if (order.getOrderItems().stream().anyMatch(item -> item.getQuantity() > item.getArticle().getStock())) {
            return false;
        }
        orders.add(order);
        order.getOrderItems().forEach(item -> {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
        });
        return true;
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    private boolean isValidEmail(String email) {
        return email != null && email.contains("@");
    }
}