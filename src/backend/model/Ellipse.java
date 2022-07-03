package backend.model;

public class Ellipse extends Figure {

    protected final double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, double sMayorAxis, double sMinorAxis) {
        super(centerPoint);
        this.sMayorAxis = sMayorAxis;
        this.sMinorAxis = sMinorAxis;
    }

    @Override
    public String toString() {
        return String.format("Elipse [Centro: %s, DMayor: %.2f, DMenor: %.2f]",getCenterPoint(), sMayorAxis, sMinorAxis);
    }

    public Point getCenterPoint() {
        return super.getCenterPoint();
    }

    public double getsMayorAxis() {
        return sMayorAxis;
    }

    public double getsMinorAxis() {
        return sMinorAxis;
    }

    @Override
    public void move(double diffX, double diffY) {
        setCenterPoint(new Point(getCenterPoint().getX()-diffX,getCenterPoint().getY()-diffY));
    }
}
