package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public abstract class Instruction {

    //guardamos la figura que se edito para poder hacer el tostring mas facil
    private final FrontFigures figureState;

    public Instruction( FrontFigures figureState) {
        this.figureState = figureState;
    }


    //dependiendo de la instruccion hace undo o redo
    public abstract void undo();
    public abstract void redo();


    public FrontFigures getFigureState() {
        return figureState;
    }

}
