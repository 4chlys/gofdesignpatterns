package be.kdg.se2.gofdesignpatterns.creational.singleton;

final class Singleton {
    private static Singleton instance;

    private Singleton() {
        // Private constructor prevents instantiation
    }

    public static synchronized Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void operation() {
        System.out.println("Singleton operation");
    }
}
