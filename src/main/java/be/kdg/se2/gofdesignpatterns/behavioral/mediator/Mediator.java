package be.kdg.se2.gofdesignpatterns.behavioral.mediator;

interface Mediator {
    void send(String message, Colleague colleague);
}
