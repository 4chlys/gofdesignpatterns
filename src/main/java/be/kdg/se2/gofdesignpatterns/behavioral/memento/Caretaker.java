package be.kdg.se2.gofdesignpatterns.behavioral.memento;

class Caretaker {
    private java.util.List<Memento> mementos = new java.util.ArrayList<>();

    public void addMemento(Memento memento) {
        mementos.add(memento);
    }

    public Memento getMemento(int index) {
        return mementos.get(index);
    }
}
