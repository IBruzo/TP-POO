package backend.model;

public class Square extends Rectangle {


    public Square(Point topLeft, double size) {
        super(topLeft,new Point(topLeft.x + size, topLeft.y + size));
    }

    public Point getTopLeft() {
        return super.getTopLeft();
    }

    public Point getBottomRight() {
        return super.getBottomRight();
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ] center:%s", getTopLeft(), getBottomRight(), getCenterPoint());
    }

}
