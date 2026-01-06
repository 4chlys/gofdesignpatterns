package be.kdg.se2.gofdesignpatterns.behavioral.strategy;

class StrategyContext {
    private Strategy strategy;

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void executeStrategy() {
        strategy.algorithmInterface();
    }
}
