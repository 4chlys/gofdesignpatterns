package be.kdg.se2.gofdesignpatterns.creational.abstractfactory;

class ConcreteFactory1 implements AbstractFactory {
    public AbstractProductA createProductA() {
        return new ProductA1();
    }

    public AbstractProductB createProductB() {
        return new ProductB1();
    }
}
