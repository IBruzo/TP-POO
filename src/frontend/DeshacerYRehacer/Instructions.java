package frontend.DeshacerYRehacer;

import frontend.FrontFigure.FrontFigures;

public class Instructions {

    private String instruction;
    private FrontFigures figureState;
    private  int index;

    public Instructions(String instruction, FrontFigures figureState) {
        this.instruction = instruction;
        this.figureState = figureState;
    }

    public String getInstruction() {
        return instruction;
    }

    public int getIndex() {
        return index;
    }

    public FrontFigures getFigureState() {
        return figureState;
    }

    @Override
    public String toString() {
        return String.format("%s %s",instruction,figureState.toString());
    }
}
