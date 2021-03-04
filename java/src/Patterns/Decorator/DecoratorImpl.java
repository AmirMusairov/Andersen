package Patterns.Decorator;

public class DecoratorImpl {
    public static void main(String[] args) {
        Car speedCar = new SpeedCar(new SimpleCar());
        speedCar.assemble();
        System.out.println(" ");

        Car businessCar = new SpeedCar(new BusinessCar(new SimpleCar()));
        businessCar.assemble();
    }
}
