package be.kdg.se2.gofdesignpatterns.behavioral.template;

abstract class AbstractClass {
    public final void templateMethod() {
        primitiveOperation1();
        primitiveOperation2();
        hook();
    }

    protected abstract void primitiveOperation1();

    protected abstract void primitiveOperation2();

    protected void hook() {
        // Optional hook method
    }
}
