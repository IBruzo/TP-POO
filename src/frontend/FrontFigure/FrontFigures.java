package frontend.FrontFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import backend.model.Figure;

public abstract class FrontFigures {
    protected final Figure figureBack;
    protected Color fillColor;
    protected Color edgeColor;
    protected Double edgeWidth;
    private final int index;

    public FrontFigures(Figure figure, Color fillColor, Color edgeColor, Double edgeWidth, int index) {
        this.figureBack = figure;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        this.edgeWidth = edgeWidth;
        this.index=index;
    }

    //TODO metodo default para no repetir tanto codigo en los draw
    // (me genera desconfiaza no poder usar el draw del rectangulo para el draw del cuadrado -Bruzo)

    public Figure getFigureBack() {
        return figureBack;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public Color getEdgeColor() {
        return edgeColor;
    }

    public int getIndex() {
        return index;
    }

    public  abstract void draw(GraphicsContext gc, Color selected);
    public abstract void draw(GraphicsContext gc);

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public void setEdgeWidth(Double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }

}
