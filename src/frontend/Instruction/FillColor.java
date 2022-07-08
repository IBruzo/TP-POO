package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;
import javafx.scene.paint.Color;


public class FillColor extends Instruction{

    private Color oldColor;
    private  Color newColor;

    public FillColor(FrontFigures figureState, Color oldColor, Color newColor) {
        super( figureState);
        this.oldColor=oldColor;
        this.newColor=newColor;
    }

    @Override
    public void undo() {
        getFigureState().setFillColor(oldColor);
    }

    @Override
    public void redo() {
        getFigureState().setFillColor(newColor);
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de relleno de %s", getFigureState().toString());
    }
}
