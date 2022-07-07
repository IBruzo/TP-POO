package frontend.FrontFigure;
import javafx.scene.paint.Color;
import backend.model.Figure;

public class FrontFigures {
    private final Figure figureBack;
    private Color fillColor;
    private Color edgeColor;
    private Double edgeWidth;

    public FrontFigures(Figure figure, Color fillColor, Color edgeColor, Double edgeWidth) {
        this.figureBack = figure;
        this.fillColor = fillColor;
        this.edgeColor = edgeColor;
        this.edgeWidth = edgeWidth;
    }
}
