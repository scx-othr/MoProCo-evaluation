class Customer {
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
        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            if (article.getStock() < item.getQuantity()) {
                return false;
            }
        }

        for (OrderItem item : order.getOrderItems()) {
            Article article = item.getArticle();
            article.setStock(article.getStock() - item.getQuantity());
        }

        orders.add(order);
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
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }
}