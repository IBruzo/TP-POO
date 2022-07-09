package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public class Achicar extends Instruction{

    private static final double AGRANDAR_10=1.1;
    private static final double ACHICAR_10=0.9;

    public Achicar(FrontFigures figureState) {
        super(figureState);
    }

    @Override
    public void undo() {
        getFigureState().getFigureBack().changeSize( AGRANDAR_10);
    }

    @Override
    public void redo() {
        getFigureState().getFigureBack().changeSize(ACHICAR_10);
    }

    @Override
    public String toString() {
        return String.format("Achicar %s",getFigureState().toString());
    }
}
