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

}
