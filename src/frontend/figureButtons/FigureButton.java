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
     * @param fillColor color con el que se rellena la fijura
     * @param edgeColor color del borde de la figura
     * @param slider grosor del borde de la figura
     * @param index posicion en la lista del canvas state para poder saber en q e profundida se encuantra del canvas
     * @return una nueva instancia de una figura de front
     */
    public abstract FrontFigures makeFigure(Point startPoint, Point endPoint, Color fillColor, Color edgeColor, Double slider,int index);
}
