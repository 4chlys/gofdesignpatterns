package be.kdg.se2.gofdesignpatterns.structural.composite;

class Composite implements Component {
    private java.util.List<Component> children = new java.util.ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public void operation() {
        System.out.println("Composite operation");
        for (Component child : children) {
            child.operation();
        }
    }
}
