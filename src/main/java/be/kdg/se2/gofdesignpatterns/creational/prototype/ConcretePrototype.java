package be.kdg.se2.gofdesignpatterns.creational.prototype;

class ConcretePrototype implements Prototype {
    private String field;

    public ConcretePrototype(String field) {
        this.field = field;
    }

    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }
}
