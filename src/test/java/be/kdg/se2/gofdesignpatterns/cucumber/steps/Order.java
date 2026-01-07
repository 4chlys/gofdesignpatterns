package be.kdg.se2.gofdesignpatterns.cucumber.steps;

class Order {
    private final java.util.List<OrderItem> items = new java.util.ArrayList<>();

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public int getItemCount() {
        return items.size();
    }

    public java.util.List<OrderItem> getItems() {
        return items;
    }
}
