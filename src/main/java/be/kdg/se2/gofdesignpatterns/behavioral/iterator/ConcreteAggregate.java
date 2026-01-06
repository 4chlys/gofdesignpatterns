package be.kdg.se2.gofdesignpatterns.behavioral.iterator;

class ConcreteAggregate<T> implements Aggregate<T> {
    private java.util.List<T> items = new java.util.ArrayList<>();

    public void add(T item) {
        items.add(item);
    }

    public Iterator<T> createIterator() {
        return new ConcreteIterator<>(items);
    }
}
