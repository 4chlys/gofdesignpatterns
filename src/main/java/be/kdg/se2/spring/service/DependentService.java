package be.kdg.se2.spring.service;

import be.kdg.se2.spring.interfaces.RepositoryInterface;

public class DependentService {
    private final RepositoryInterface repository;

    public DependentService(RepositoryInterface repository) {
        this.repository = repository;
    }
}
