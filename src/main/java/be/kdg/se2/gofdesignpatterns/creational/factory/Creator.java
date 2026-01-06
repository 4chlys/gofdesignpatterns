package be.kdg.se2.gofdesignpatterns.creational.factory;

abstract class Creator {
    public abstract Product factoryMethod();

    public void operation() {
        Product product = factoryMethod();
        product.use();
    }
}
