package be.kdg.se2.gofdesignpatterns.structural.decorator;

class ConcreteComponent implements ComponentInterface {
    public void operation() {
        System.out.println("Concrete component operation");
    }
}
