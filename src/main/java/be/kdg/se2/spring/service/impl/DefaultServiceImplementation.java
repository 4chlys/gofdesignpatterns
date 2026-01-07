package be.kdg.se2.spring.service.impl;

import be.kdg.se2.spring.interfaces.ServiceInterface;

public class DefaultServiceImplementation implements ServiceInterface {
    @Override
    public void performOperation() {
        System.out.println("Default service operation");
    }
}
