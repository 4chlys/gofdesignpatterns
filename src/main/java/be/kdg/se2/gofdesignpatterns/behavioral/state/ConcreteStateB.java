package be.kdg.se2.gofdesignpatterns.behavioral.state;

class ConcreteStateB implements State {
    public void handle(Context context) {
        System.out.println("State B handling");
        context.setState(new ConcreteStateA());
    }
}
