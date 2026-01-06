package be.kdg.se2.gofdesignpatterns.behavioral.iterator;

class ConcreteIterator<T> implements Iterator<T> {
    private java.util.List<T> items;
    private int position = 0;

    public ConcreteIterator(java.util.List<T> items) {
        this.items = items;
    }

    public boolean hasNext() {
        return position < items.size();
    }

    public T next() {
        return items.get(position++);
    }
}
