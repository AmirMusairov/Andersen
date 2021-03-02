package Patterns.Decorator;

public class SimpleCar implements Car {
    @Override
    public void assemble() {
        System.out.println("Simple car");
    }
}
