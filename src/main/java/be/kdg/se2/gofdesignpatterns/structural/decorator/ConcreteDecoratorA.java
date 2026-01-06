package be.kdg.se2.gofdesignpatterns.structural.decorator;

class ConcreteDecoratorA extends Decorator {
    public ConcreteDecoratorA(ComponentInterface component) {
        super(component);
    }

    public void operation() {
        super.operation();
        addedBehavior();
    }

    private void addedBehavior() {
        System.out.println("Added behavior A");
    }
}
