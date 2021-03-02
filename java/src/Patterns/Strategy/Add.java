package Patterns.Strategy;

public class Add implements MathStrategy {
    @Override
    public int operation(int num1, int num2) {
        return num1 + num2;
    }
}
