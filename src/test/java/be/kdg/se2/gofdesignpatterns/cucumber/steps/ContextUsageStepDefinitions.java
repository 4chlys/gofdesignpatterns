package be.kdg.se2.gofdesignpatterns.cucumber.steps;

import be.kdg.se2.gofdesignpatterns.cucumber.context.ScenarioContext;
import io.cucumber.java.en.*;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Template demonstrating ScenarioContext usage for sharing state.
 *
 * Place in: src/test/java/be/kdg/se2/templates/cucumber/steps/
 */
public class ContextUsageStepDefinitions {

    @Autowired
    private ScenarioContext scenarioContext;

    /**
     * Store data in context for use in later steps.
     */
    @Given("I create a user with username {string}")
    public void createUser(String username) {
        User user = new User(username);
        scenarioContext.setContext("currentUser", user);
    }

    /**
     * Retrieve and use data from context.
     */
    @When("the user submits a request")
    public void userSubmitsRequest() {
        User user = scenarioContext.getContext("currentUser", User.class);
        assertNotNull(user, "User should be in context");

        String response = "Request from " + user.getUsername();
        scenarioContext.setContext("response", response);
    }

    /**
     * Verify data stored in context.
     */
    @Then("the response should contain the username")
    public void responseContainsUsername() {
        String response = scenarioContext.getContext("response", String.class);
        User user = scenarioContext.getContext("currentUser", User.class);

        assertNotNull(response);
        assertNotNull(user);
        assertTrue(response.contains(user.getUsername()));
    }

    /**
     * Store complex object in context.
     */
    @Given("I have an order with {int} items")
    public void createOrder(Integer itemCount) {
        Order order = new Order();
        for (int i = 0; i < itemCount; i++) {
            order.addItem(new OrderItem("Item " + i, 10.0));
        }
        scenarioContext.setContext("order", order);
    }

    /**
     * Modify object stored in context.
     */
    @When("I add item {string} with price {double}")
    public void addItemToOrder(String itemName, Double price) {
        Order order = scenarioContext.getContext("order", Order.class);
        assertNotNull(order, "Order should be in context");

        order.addItem(new OrderItem(itemName, price));
    }

    /**
     * Verify modified object.
     */
    @Then("the order should have {int} items")
    public void orderHasItems(Integer expectedCount) {
        Order order = scenarioContext.getContext("order", Order.class);
        assertNotNull(order);
        assertEquals(expectedCount, order.getItemCount());
    }

    /**
     * Store multiple values in context.
     */
    @Given("I have session data")
    public void setupSessionData() {
        scenarioContext.setContext("sessionId", "SESSION-123");
        scenarioContext.setContext("userId", 42);
        scenarioContext.setContext("authenticated", true);
    }

    /**
     * Use multiple values from context.
     */
    @Then("the session should be valid")
    public void sessionValid() {
        String sessionId = scenarioContext.getContext("sessionId", String.class);
        Integer userId = scenarioContext.getContext("userId", Integer.class);
        Boolean authenticated = scenarioContext.getContext("authenticated", Boolean.class);

        assertNotNull(sessionId);
        assertNotNull(userId);
        assertTrue(authenticated);
    }
}

