package be.kdg.se2.gofdesignpatterns.structural.proxy;

class RealSubject implements Subject {
    @Override
    public void request() {
        System.out.println("Real subject request");
    }
}
