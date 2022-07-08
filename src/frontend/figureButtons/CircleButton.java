package frontend.figureButtons;

import backend.model.Point;
import frontend.FrontFigure.CircleFront;
import frontend.FrontFigure.FrontFigures;
import javafx.scene.paint.Color;

public class CircleButton extends FigureButton {

    public CircleButton(String name) {
        super(name);
    }

    @Override
    public FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider, int index){
        double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
        return new CircleFront(startPoint, circleRadius,fillColor,edgeColor,slider,index);
    }
}
