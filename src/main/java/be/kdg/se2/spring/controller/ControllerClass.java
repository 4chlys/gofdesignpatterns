package be.kdg.se2.spring.controller;

import be.kdg.se2.spring.interfaces.ServiceInterface;

/**
 * Additional supporting classes.
 */
public class ControllerClass {
    private final ServiceInterface service;

    public ControllerClass(ServiceInterface service) {
        this.service = service;
    }

    public void handleRequest() {
        service.performOperation();
    }
}
