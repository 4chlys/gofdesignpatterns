package be.kdg.se2.gofdesignpatterns.behavioral.chainofresponsibility;

class ConcreteHandler1 extends Handler {
    public void handleRequest(int request) {
        if (request < 10) {
            System.out.println("Handler1 handled request " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
