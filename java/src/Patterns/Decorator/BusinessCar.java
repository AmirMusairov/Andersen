package Patterns.Decorator;

public class BusinessCar extends CarDecorator {

    public BusinessCar(Car c) {
        super(c);
    }

    @Override
    public void assemble() {
        super.assemble();
        System.out.println("Adding details for business car");
    }
}