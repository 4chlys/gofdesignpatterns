package be.kdg.se2.gofdesignpatterns.creational.factory;

class ConcreteCreatorA extends Creator {
    public Product factoryMethod() {
        return new ConcreteProductA();
    }
}
