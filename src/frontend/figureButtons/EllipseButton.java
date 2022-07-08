package frontend.figureButtons;

import backend.model.Point;
import frontend.FrontFigure.EllipseFront;
import frontend.FrontFigure.FrontFigures;
import javafx.scene.paint.Color;

public class EllipseButton extends FigureButton {

    public EllipseButton(String name) {
        super(name);
    }

    @Override
    public FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider, int index) {
        Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
        double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
        double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
        return new EllipseFront(centerPoint, sMayorAxis, sMinorAxis,fillColor,edgeColor,slider,index);
    }
}
