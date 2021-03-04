package Patterns.Factory;

public class FactoryImpl {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();

        Shape figure1 = shapeFactory.getShape("Rectangle");
        figure1.draw();

        Shape figure2 = shapeFactory.getShape("Square");
        figure2.draw();

        Shape figure3 = shapeFactory.getShape("Circle");
        figure3.draw();

    }
}
