package be.kdg.se2.gofdesignpatterns.behavioral.iterator;

interface Aggregate<T> {
    Iterator<T> createIterator();
}
