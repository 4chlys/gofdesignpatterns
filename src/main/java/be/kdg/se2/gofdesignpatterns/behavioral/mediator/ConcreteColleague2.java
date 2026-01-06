package be.kdg.se2.gofdesignpatterns.behavioral.mediator;

class ConcreteColleague2 extends Colleague {
    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    public void receive(String message) {
        System.out.println("Colleague2 received: " + message);
    }

    public void send(String message) {
        mediator.send(message, this);
    }
}
