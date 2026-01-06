package be.kdg.se2.gofdesignpatterns.structural.decorator;

abstract class Decorator implements ComponentInterface {
    protected ComponentInterface component;

    public Decorator(ComponentInterface component) {
        this.component = component;
    }

    public void operation() {
        component.operation();
    }
}
