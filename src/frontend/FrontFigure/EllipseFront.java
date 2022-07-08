package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Ellipse;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class EllipseFront extends FrontFigures {
    public EllipseFront(Point centerPoint, Double sMayorAxis, Double sMinorAxis, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Ellipse(centerPoint, sMayorAxis, sMinorAxis), fillColor, edgeColor, edgeWidth);
    }

    public void draw(GraphicsContext gc) {
        draw(gc,edgeColor);
    }

    @Override
    public String toString() {
        return "Elipse";
    }

    @Override
    public void draw(GraphicsContext gc, Color selected) {
        Ellipse ellipse = (Ellipse) figureBack;
        gc.setFill(this.fillColor);
        gc.setStroke(selected);
        gc.setLineWidth(edgeWidth);
        gc.strokeOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
        gc.fillOval(ellipse.getCenterPoint().getX() - (ellipse.getsMayorAxis() / 2), ellipse.getCenterPoint().getY() - (ellipse.getsMinorAxis() / 2), ellipse.getsMayorAxis(), ellipse.getsMinorAxis());
    }
}
