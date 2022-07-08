package backend.model;

public class Rectangle extends Figure {

    private Point topLeft, bottomRight;

    public Rectangle(Point topLeft, Point bottomRight) {
        super(new Point((bottomRight.getX()-topLeft.getX())/2 + topLeft.getX(), (bottomRight.getY()- topLeft.getY())/2 + topLeft.getY()));
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public Point getTopLeft() {
        return topLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Rectángulo [ %s , %s ] center: %s", topLeft, bottomRight, getCenterPoint());
    }

    public void setCenterPoint(Point topLeft, Point bottomRight){
       super.setCenterPoint(new Point((bottomRight.getX()-topLeft.getX())/2 + topLeft.getX(), (bottomRight.getY()- topLeft.getY())/2 + topLeft.getY()));
    }



    @Override
    public void move(double diffX,double diffY){
       setTopLeft(new Point(getTopLeft().getX() + diffX,getTopLeft().getY() + diffY ));
       setBottomRight(new Point(getBottomRight().getX() + diffX,getBottomRight().getY() + diffY));
       setCenterPoint(getTopLeft(),getBottomRight());
    }

    @Override
    public boolean isInFigure(Point eventPoint) {
        return eventPoint.getX() > getTopLeft().getX() && eventPoint.getX() < getBottomRight().getX() &&
                eventPoint.getY() > getTopLeft().getY() && eventPoint.getY() < getBottomRight().getY();
    }

    @Override
    public void changeSize(double amount) {
        double diffX = getCenterPoint().getX()-topLeft.getX();
        double diffY = getCenterPoint().getY()-topLeft.getY();
        getTopLeft().movePoint(diffX*(1.0-amount),diffY*(1.0-amount));
        getBottomRight().movePoint(diffX*(amount-1.0),diffY*(amount-1.0));
    }
}
