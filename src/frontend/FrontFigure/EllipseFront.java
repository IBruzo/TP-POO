package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Ellipse;
import javafx.scene.paint.Color;

public class EllipseFront extends FrontFigures {
    public EllipseFront(Point centerPoint, Double sMayorAxis, Double sMinorAxis, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Ellipse(centerPoint, sMayorAxis, sMinorAxis), fillColor, edgeColor, edgeWidth);
    }
}
