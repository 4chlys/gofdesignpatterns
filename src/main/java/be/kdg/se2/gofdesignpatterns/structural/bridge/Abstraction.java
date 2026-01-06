package be.kdg.se2.gofdesignpatterns.structural.bridge;

abstract class Abstraction {
    protected Implementor implementor;

    protected Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
