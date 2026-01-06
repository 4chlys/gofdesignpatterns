package be.kdg.se2.gofdesignpatterns.behavioral.visitor;

interface Element {
    void accept(Visitor visitor);
}
