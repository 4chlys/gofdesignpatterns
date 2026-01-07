package be.kdg.se2.spring.service.impl;

import be.kdg.se2.spring.interfaces.ServiceInterface;

/**
 * Supporting implementation classes.
 */
public class ServiceImplementation implements ServiceInterface {
    @Override
    public void performOperation() {
        System.out.println("Service operation performed");
    }
}
