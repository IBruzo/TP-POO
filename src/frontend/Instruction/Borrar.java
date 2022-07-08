package frontend.Instruction;

import backend.CanvasState;
import frontend.FrontFigure.FrontFigures;

public class Borrar extends Instruction{

    private CanvasState state;

    public Borrar(FrontFigures figureState, CanvasState state) {
        super(figureState);
        this.state=state;
    }

    @Override
    public void undo() {
        state.setFigure(getFigureState().getIndex(), getFigureState());
    }

    @Override
    public void redo() {
        state.deleteFigure(getFigureState());
    }

    @Override
    public String toString() {
        return String.format("Borrar %s",getFigureState());
    }
}
