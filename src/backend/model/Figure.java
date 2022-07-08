package backend.model;



public abstract class Figure {
    private Point centerPoint;

    public Figure(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }


    public abstract void move(double diffX,double diffY);

    public abstract boolean isInFigure(Point eventPoint);

    public abstract void changeSize(double amount);
}
