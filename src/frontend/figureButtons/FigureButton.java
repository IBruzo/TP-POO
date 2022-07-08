package frontend.figureButtons;

import backend.model.Point;
import frontend.FrontFigure.FrontFigures;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;


public abstract class FigureButton extends ToggleButton {


    public FigureButton(String name) {
        super(name);
    }

    public abstract FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider,int index);
}
