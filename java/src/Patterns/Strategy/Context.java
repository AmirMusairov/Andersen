package Patterns.Strategy;

public class Context {
    private MathStrategy strategy;

    public Context(MathStrategy strategy) {
        this.strategy = strategy;
    }

    public int executeMathStrategy(int num1, int num2) {
        return strategy.operation(num1, num2);
    }
}
