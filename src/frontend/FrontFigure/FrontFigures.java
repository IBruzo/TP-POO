package frontend.FrontFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import backend.model.Figure;

public abstract class FrontFigures {
    protected final Figure figureBack;
    protected Color fillColor;
    protected Color edgeColor;
    protected Double edgeWidth;

    public FrontFigures(Figure figure, Color fillColor, Color edgeColor, Double edgeWidth) {
        this.figureBack = figure;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        this.edgeWidth = edgeWidth;
    }

    public Figure getFigureBack() {
        return figureBack;
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

    @Override
    public String toString() {
        return figureBack.toString();
    }
}
