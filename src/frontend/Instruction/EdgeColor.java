package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

import javafx.scene.paint.Color;

public class EdgeColor  extends Instruction{

    private Color oldColor;
    private Color newColor;

    public EdgeColor(FrontFigures figureState, Color oldColor, Color newColor) {
        super(figureState);
        this.oldColor=oldColor;
        this.newColor=newColor;
    }

    @Override
    public void undo() {
        getFigureState().setEdgeColor(oldColor);
    }

    @Override
    public void redo() {
        getFigureState().setEdgeColor(newColor);
    }

    @Override
    public String toString() {
        return String.format("Cambiar color de borde de %s", getFigureState().toString());
    }
}
