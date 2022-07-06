package backend.model;


import javafx.scene.paint.Color;

public abstract class Figure {
    private Point centerPoint;
    private Color fillColor;
    private Color edgeColor;
    private Double edgeWidth;
    private static final Color DEFAULT_FILLCOLOR= Color.YELLOW;
    private static final Color DEFUALT_EDGECOLOR=Color.BLACK;

    public Figure(Point centerPoint, Color fillColor, Color edgeColor,Double edgeWidth) {
        this.centerPoint = centerPoint;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        this.edgeWidth=edgeWidth;
    }

    public Figure(Point centerPoint) {
        this(centerPoint,DEFAULT_FILLCOLOR,DEFUALT_EDGECOLOR,1.0);
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public Double getEdgeWidth() {
        return edgeWidth;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setEdgeWidth(Double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public abstract void move(double diffX,double diffY);

    public abstract boolean isInFigure(Point eventPoint);
}
