package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public abstract class Instruction {

    private final FrontFigures figureState;

    public Instruction( FrontFigures figureState) {
        this.figureState = figureState;
    }

    public abstract void undo();
    public abstract void redo();


    public FrontFigures getFigureState() {
        return figureState;
    }

}
