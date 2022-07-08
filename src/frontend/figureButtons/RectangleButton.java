package frontend.figureButtons;

import backend.model.Point;

import frontend.FrontFigure.FrontFigures;
import frontend.FrontFigure.RectangleFront;
import javafx.scene.paint.Color;


public class RectangleButton extends FigureButton {

    public RectangleButton(String name) {
        super(name);
    }

    @Override
    public FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider, int index) {
      return new RectangleFront(startPoint, endPoint,fillColor,edgeColor,slider,index);
    }
}
