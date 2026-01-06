package be.kdg.se2.gofdesignpatterns.behavioral.observer;

class ConcreteObserver implements Observer {
    private String name;

    public ConcreteObserver(String name) {
        this.name = name;
    }

    public void update(String state) {
        System.out.println(name + " received update: " + state);
    }
}
