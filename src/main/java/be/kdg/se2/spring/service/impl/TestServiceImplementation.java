package be.kdg.se2.spring.service.impl;

import be.kdg.se2.spring.interfaces.ServiceInterface;

public class TestServiceImplementation implements ServiceInterface {
    @Override
    public void performOperation() {
        System.out.println("Test service operation");
    }
}
