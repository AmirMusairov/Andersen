package Patterns.Strategy;

public class StrategyImpl {
    public static void main(String[] args) {
        Context context = new Context(new Add());
        System.out.println("1 + 0 = " + context.executeMathStrategy(1, 0));

        context = new Context(new Substract());
        System.out.println("2 - 1 = " + context.executeMathStrategy(2, 1));

        context = new Context((new Multiply()));
        System.out.println("3 * 2 = " + context.executeMathStrategy(3, 2));

    }
}
