package be.kdg.se2.gofdesignpatterns.creational.factory;

class ConcreteCreatorB extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductB();
    }
}
