package be.kdg.se2.gofdesignpatterns.creational.builder;

interface Builder {
    void buildPartA();

    void buildPartB();

    void buildPartC();

    Product getResult();
}
