package be.kdg.se2.gofdesignpatterns.behavioral.visitor;

class ConcreteElementA implements Element {
    public void accept(Visitor visitor) {
        visitor.visitConcreteElementA(this);
    }
}
