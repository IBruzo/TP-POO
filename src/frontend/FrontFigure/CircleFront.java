package frontend.FrontFigure;

import backend.model.Circle;
import backend.model.Point;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class CircleFront extends FrontFigures {

    public CircleFront(Point centerPoint, Double radius, Color fillColor, Color edgeColor, Double edgeWidth) {
        super(new Circle(centerPoint, radius), fillColor, edgeColor, edgeWidth);
    }

    @Override
    public void draw(GraphicsContext gc) {
        draw(gc,edgeColor);
    }

    @Override
    public void draw(GraphicsContext gc, Color selected) {
        Circle circle = (Circle) figureBack;
        gc.setFill(this.fillColor);
        gc.setStroke(selected);
        double diameter = circle.getRadius() * 2;
        gc.fillOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);
        gc.strokeOval(circle.getCenterPoint().getX() - circle.getRadius(), circle.getCenterPoint().getY() - circle.getRadius(), diameter, diameter);

    }
}
