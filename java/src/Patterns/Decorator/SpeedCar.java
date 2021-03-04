package Patterns.Decorator;

public class SpeedCar extends CarDecorator {

    public SpeedCar(Car c) {
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding details for sport car");
    }
}
