package backend;

import backend.model.Figure;

import java.util.ArrayList;
import java.util.List;
//solo maneja las figuras q tiene el canvas para ver si estan o no
public class CanvasState {

    private final List<Figure> list = new ArrayList<>();

    public void addFigure(Figure figure) {
        list.add(figure);
    }

    public void deleteFigure(Figure figure) {
        list.remove(figure);
    }

    public Iterable<Figure> figures() {
        return new ArrayList<>(list);
    }

}
