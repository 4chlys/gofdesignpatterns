package be.kdg.se2.gofdesignpatterns.behavioral.mediator;

class ConcreteColleague1 extends Colleague {
    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    public void receive(String message) {
        System.out.println("Colleague1 received: " + message);
    }

    public void send(String message) {
        mediator.send(message, this);
    }
}
