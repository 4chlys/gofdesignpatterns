package be.kdg.se2.gofdesignpatterns.behavioral.visitor;

class ConcreteVisitor implements Visitor {
    public void visitConcreteElementA(ConcreteElementA element) {
        System.out.println("Visiting element A");
    }

    public void visitConcreteElementB(ConcreteElementB element) {
        System.out.println("Visiting element B");
    }
}
