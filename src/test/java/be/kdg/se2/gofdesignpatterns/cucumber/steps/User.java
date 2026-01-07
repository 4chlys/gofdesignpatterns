package be.kdg.se2.gofdesignpatterns.cucumber.steps;

/**
 * Supporting classes for context examples.
 */
class User {
    private String username;

    public User(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
