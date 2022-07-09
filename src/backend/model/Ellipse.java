package backend.model;

public class Ellipse extends Figure {

    protected double sMayorAxis, sMinorAxis;

    public Ellipse(Point centerPoint, Double sMayorAxis, Double sMinorAxis) {
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
        setCenterPoint(new Point(getCenterPoint().getX()+diffX,getCenterPoint().getY()+diffY));
    }

    protected double pointInArea(Point eventPoint){
        return (Math.pow(eventPoint.getX() - getCenterPoint().getX(), 2) / Math.pow(getsMayorAxis(), 2)) +
                (Math.pow(eventPoint.getY() - getCenterPoint().getY(), 2) / Math.pow(getsMinorAxis(), 2));
    }

    @Override
    public boolean isInFigure(Point eventPoint) {
        return (pointInArea(eventPoint)) <= 0.3;
    }

    @Override
    public void changeSize(double amount) {
            sMayorAxis*=amount;
            sMinorAxis*=amount;
    }
}
