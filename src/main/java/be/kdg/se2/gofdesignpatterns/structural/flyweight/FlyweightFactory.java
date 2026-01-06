package be.kdg.se2.gofdesignpatterns.structural.flyweight;

class FlyweightFactory {
    private java.util.Map<String, Flyweight> flyweights = new java.util.HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new ConcreteFlyweight(key));
        }
        return flyweights.get(key);
    }
}
