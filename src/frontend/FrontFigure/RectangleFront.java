package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.paint.Color;
import java.awt.*;

public class RectangleFront extends FrontFigures {
    public RectangleFront(Point topLeft, Point bottomRight, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Rectangle(topLeft, bottomRight), fillColor, edgeColor, edgeWidth);
    }

}
