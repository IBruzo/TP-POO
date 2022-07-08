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
