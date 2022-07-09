package frontend.Instruction;

import backend.CanvasState;
import frontend.FrontFigure.FrontFigures;

//como borrar es lo opuesto a dibujar la extendimos para reeeutilizar el comportamineto de las clases, swichando el undo y redo
public class Borrar extends Dibujar{


    public Borrar(FrontFigures figureState, CanvasState state) {
        super(figureState,state);
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
        return String.format("Borrar %s",getFigureState());
    }
}
