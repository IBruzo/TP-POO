package frontend.Instruction;

import backend.CanvasState;
import frontend.FrontFigure.FrontFigures;

public class Dibujar extends Instruction{

    private CanvasState state;

    public Dibujar(FrontFigures figureState, CanvasState state) {
        super(figureState);
        this.state=state;
    }

    @Override
    public void undo() {//borra la figura
        state.deleteFigure(getFigureState());
    }

    @Override
    public void redo() {//la dibuja
        state.setFigure(getFigureState().getIndex(), getFigureState());
    }

    @Override
    public String toString() {
        return String.format("Dibujar %s",getFigureState().toString());
    }
}
