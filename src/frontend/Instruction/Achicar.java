package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public class Achicar extends Instruction{

    public Achicar(FrontFigures figureState) {
        super(figureState);
    }

    @Override
    public void undo() {
        getFigureState().getFigureBack().changeSize(1.1);
    }

    @Override
    public void redo() {
        getFigureState().getFigureBack().changeSize(0.9);
    }

    @Override
    public String toString() {
        return String.format("Achicar %s",getFigureState().toString());
    }
}
