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

    public abstract void draw(GraphicsContext gc);
}
