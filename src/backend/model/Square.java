package backend.model;

public class Square extends Rectangle {


    public Square(Point topLeft, Double size) {
        super(topLeft,new Point(topLeft.getX() + size, topLeft.getY() + size));
    }

    public Point getTopLeft() {
        return super.getTopLeft();
    }

    public Point getBottomRight() {
        return super.getBottomRight();
    }

    @Override
    public String toString() {
        return String.format("Cuadrado [ %s , %s ]", getTopLeft(), getBottomRight());
    }

}
