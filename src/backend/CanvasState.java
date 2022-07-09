package backend;

import frontend.FrontFigure.FrontFigures;

import java.util.ArrayList;
import java.util.List;
//solo maneja las figuras q tiene el canvas para ver si estan o no
public class CanvasState {

    private final List<FrontFigures> list = new ArrayList<>(); //FIFO

    public void addFigure(FrontFigures figure) {
        list.add(figure);
    }

    /**
     * agrega en determinado indice de la lista la figura
     * si la lista esta en 0 solo lo agraga a la lista
     * @param index
     * @param figure
     */
    public void setFigure(int index ,FrontFigures figure) {
        if(list.size()==0)
            addFigure(figure);
        else
            list.set(index,figure);
    }

    public int size(){
        return list.size();
    }

    public void deleteFigure(FrontFigures figure) {
        list.remove(figure);
    }

    public Iterable<FrontFigures> figures() {
        return new ArrayList<>(list);
    }

}
