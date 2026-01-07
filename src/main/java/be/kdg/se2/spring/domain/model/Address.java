package be.kdg.se2.spring.domain.model;

public class Address {
    private final String country;

    public Address(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
