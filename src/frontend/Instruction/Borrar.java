package frontend.Instruction;

import backend.CanvasState;
import frontend.FrontFigure.FrontFigures;

public class Borrar extends Dibujar{


    public Borrar(FrontFigures figureState, CanvasState state) {
        super(figureState,state);
    }

    @Override
    public void undo() {
        super.redo();
    }

    @Override
    public void redo() {
        super.undo();
    }

    @Override
    public String toString() {
        return String.format("Borrar %s",getFigureState());
    }
}
