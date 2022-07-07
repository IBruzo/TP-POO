package frontend.FrontFigure;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.paint.Color;

public class CircleFront extends FrontFigures {

    public CircleFront(Point centerPoint, Double radius, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Circle(centerPoint, radius), fillColor, edgeColor, edgeWidth);
    }
}
