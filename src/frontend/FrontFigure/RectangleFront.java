package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Rectangle;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import java.awt.*;

public class RectangleFront extends FrontFigures {
    public RectangleFront(Point topLeft, Point bottomRight, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Rectangle(topLeft, bottomRight), fillColor, edgeColor, edgeWidth);
    }

    public void draw(GraphicsContext gc) {
        draw(gc,edgeColor);
    }
    public void draw(GraphicsContext gc, Color selected){
        Rectangle aux = (Rectangle)figureBack;
        gc.setFill(this.fillColor);
        gc.setStroke(selected);
        gc.setLineWidth(edgeWidth);
        gc.fillRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                Math.abs(aux.getTopLeft().getX() - aux.getBottomRight().getX()), Math.abs(aux.getTopLeft().getY() - aux.getBottomRight().getY()));
        gc.strokeRect(aux.getTopLeft().getX(), aux.getTopLeft().getY(),
                Math.abs(aux.getTopLeft().getX() - aux.getBottomRight().getX()), Math.abs(aux.getTopLeft().getY() - aux.getBottomRight().getY()));

    }

}
