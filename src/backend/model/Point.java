package backend.model;



public class Point{

    private Double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void movePoint(double x, double y){
        this.x+=x;
        this.y+=y;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(!(obj instanceof Point))
            return false;
        Point other = (Point) obj;
        return x.equals(other.x) && y.equals(other.y);
    }

    @Override
    public String toString() {
        return String.format("{%.2f , %.2f}", x, y);
    }

}
