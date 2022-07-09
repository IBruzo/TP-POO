package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;

public class Agrandar extends Achicar{

    public Agrandar(FrontFigures figureState) {
        super(figureState);
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
        return String.format("Agrandar %s",getFigureState().toString());
    }
}
