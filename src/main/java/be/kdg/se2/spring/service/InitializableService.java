package be.kdg.se2.spring.service;

public class InitializableService {
    public void init() {
        System.out.println("Initializing service");
    }

    public void cleanup() {
        System.out.println("Cleaning up service");
    }
}
