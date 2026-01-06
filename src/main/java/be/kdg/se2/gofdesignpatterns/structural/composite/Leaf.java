package be.kdg.se2.gofdesignpatterns.structural.composite;

class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void operation() {
        System.out.println("Leaf " + name);
    }
}
