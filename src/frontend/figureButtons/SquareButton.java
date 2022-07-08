package frontend.figureButtons;

import backend.model.Point;
import frontend.FrontFigure.FrontFigures;
import frontend.FrontFigure.SquareFront;
import javafx.scene.paint.Color;

public class SquareButton extends FigureButton {

    public SquareButton(String name) {
        super(name);
    }

    @Override
    public FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider) {
        double size = Math.abs(endPoint.getX() - startPoint.getX());
        return new SquareFront(startPoint, size,fillColor,edgeColor,slider);
    }
}
