package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Square;
import javafx.scene.paint.Color;

public class SquareFront extends FrontFigures {
    public SquareFront(Point topLeft, Double size, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Square(topLeft, size), fillColor, edgeColor, edgeWidth);
    }
}
