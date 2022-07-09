package frontend.Instruction;

import frontend.FrontFigure.FrontFigures;


//como Agrandar es lo opuesto a Achicar la extendimos para reeeutilizar el comportamineto de las clases, swichando el undo y redo
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
