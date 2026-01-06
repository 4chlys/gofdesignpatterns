package be.kdg.se2.gofdesignpatterns.creational.builder;

class ConcreteBuilder implements Builder {
    private Product product = new Product();

    public void buildPartA() {
        product.setPartA("Part A");
    }

    public void buildPartB() {
        product.setPartB("Part B");
    }

    public void buildPartC() {
        product.setPartC("Part C");
    }

    public Product getResult() {
        return product;
    }
}
