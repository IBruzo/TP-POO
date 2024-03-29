package frontend.FrontFigure;
import backend.model.Point;
import backend.model.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SquareFront extends FrontFigures {
    public SquareFront(Point topLeft, Double size, Color fillColor, Color edgeColor, Double edgeWidth,int index) {
        super(new Square(topLeft, size), fillColor, edgeColor, edgeWidth,index);
    }


    @Override
    public String toString() {
        return "Cuadrado";
    }

    public void draw(GraphicsContext gc) {
      draw(gc,edgeColor);
    }

    public void draw(GraphicsContext gc, Color selected){
        Square square = (Square) figureBack;
        changeGraphics(gc, selected);
        gc.fillRect(square.getTopLeft().getX(), square.getTopLeft().getY(),
                Math.abs(square.getTopLeft().getX() - square.getBottomRight().getX()), Math.abs(square.getTopLeft().getY() - square.getBottomRight().getY()));
        gc.strokeRect(square.getTopLeft().getX(), square.getTopLeft().getY(), Math.abs(square.getTopLeft().getX() - square.getBottomRight().getX()), Math.abs(square.getTopLeft().getY() - square.getBottomRight().getY()));

    }
}
