package be.kdg.se2.gofdesignpatterns.behavioral.iterator;

interface Iterator<T> {
    boolean hasNext();

    T next();
}
