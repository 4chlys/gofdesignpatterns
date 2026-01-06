package be.kdg.se2.gofdesignpatterns.behavioral.state;

class ConcreteStateA implements State {
    public void handle(Context context) {
        System.out.println("State A handling");
        context.setState(new ConcreteStateB());
    }
}
