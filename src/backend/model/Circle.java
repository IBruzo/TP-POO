package backend.model;

public class Circle extends Ellipse {

    public Circle(Point centerPoint, Double radius) {
        super(centerPoint,radius,radius);
    }

    @Override
    public String toString() {
        return String.format("Círculo [Centro: %s, Radio: %.2f]", getCenterPoint(), getRadius());
    }

    public Point getCenterPoint() {
        return super.getCenterPoint();
    }

    public double getRadius() {
        return super.getsMayorAxis();
    }

    @Override
    public boolean isInFigure(Point eventPoint) {
        return pointInArea(eventPoint) <= 1.0;
    }
}
