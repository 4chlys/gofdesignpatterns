package be.kdg.se2.gofdesignpatterns.behavioral.visitor;

interface Visitor {
    void visitConcreteElementA(ConcreteElementA element);

    void visitConcreteElementB(ConcreteElementB element);
}
