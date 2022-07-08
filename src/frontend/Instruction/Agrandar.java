package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public class Agrandar extends Instruction{

    public Agrandar(FrontFigures figureState) {
        super(figureState);
    }

    @Override
    public void undo() {
        getFigureState().getFigureBack().changeSize(0.9);
    }

    @Override
    public void redo() {
        getFigureState().getFigureBack().changeSize(1.1);
    }

    @Override
    public String toString() {
        return String.format("Agrandar %s",getFigureState().toString());
    }
}
