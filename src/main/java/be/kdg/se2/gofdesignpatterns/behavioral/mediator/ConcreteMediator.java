package be.kdg.se2.gofdesignpatterns.behavioral.mediator;

class ConcreteMediator implements Mediator {
    private ConcreteColleague1 colleague1;
    private ConcreteColleague2 colleague2;

    public void setColleague1(ConcreteColleague1 colleague) {
        this.colleague1 = colleague;
    }

    public void setColleague2(ConcreteColleague2 colleague) {
        this.colleague2 = colleague;
    }

    public void send(String message, Colleague colleague) {
        if (colleague == colleague1) {
            colleague2.receive(message);
        } else {
            colleague1.receive(message);
        }
    }
}
