package Patterns.Factory;

public class ShapeFactory {

    public Shape getShape(String shapeType) {
        switch(shapeType) {
            case "Rectangle":
                return new Rectangle();
            case "Square":
                return new Square();
            case "Circle":
                return new Circle();
            default:
                return null;
        }
    }
}
