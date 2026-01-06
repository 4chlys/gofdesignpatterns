package be.kdg.se2.gofdesignpatterns.behavioral.chainofresponsibility;

class ConcreteHandler2 extends Handler {
    public void handleRequest(int request) {
        if (request >= 10 && request < 20) {
            System.out.println("Handler2 handled request " + request);
        } else if (successor != null) {
            successor.handleRequest(request);
        }
    }
}
