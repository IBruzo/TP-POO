package frontend.figureButtons;

import backend.model.Point;
import frontend.FrontFigure.FrontFigures;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;


public abstract class FigureButton extends ToggleButton {


    public FigureButton(String name) {
        super(name);
    }

    /**
     * le pasa la informacion necesaria para poder crear las figuras de front dependiendo la figura que se escogio
     * @param startPoint
     * @param endPoint
     * @param fillColor
     * @param edgeColor
     * @param slider
     * @param index
     * @return
     */
    public abstract FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider,int index);
}
