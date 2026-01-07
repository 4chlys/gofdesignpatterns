package be.kdg.se2.spring.domain.model;

public class Customer {

    private final boolean vip;

    public Customer(boolean vip) {
        this.vip = vip;
    }

    public boolean isVIP() {
        return vip;
    }
}
