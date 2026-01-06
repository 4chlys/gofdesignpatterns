package be.kdg.se2.gofdesignpatterns.behavioral.visitor;

class ConcreteElementB implements Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementB(this);
    }
}
