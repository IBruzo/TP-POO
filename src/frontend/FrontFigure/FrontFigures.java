package frontend.FrontFigure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import backend.model.Figure;

public abstract class FrontFigures {

    //elementos que describen a una figura que va a ser dibujada
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

    /**
     * sirve para no ir repitiendo codigo en cada una de las figuras de front
     * @param gc
     * @param selected color seleccionado en el picker para cambiar el color del borde
     */

    public void changeGraphics(GraphicsContext gc, Color selected){
        gc.setFill(this.fillColor);
        gc.setStroke(selected);
        gc.setLineWidth(edgeWidth);
    }

    /**
     * dibuja las figuras y las rellena dependiendo la forma y el color
     * el metodo es sobrecargado para poder tomar el caso especial cuando la figura esta seleccionada
     * @param gc
     * @param selected
     */
    public  abstract void draw(GraphicsContext gc, Color selected);

    public abstract void draw(GraphicsContext gc);



    //getters y setters de los elementos que lee el front

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public void setEdgeColor(Color edgeColor) {
        this.edgeColor = edgeColor;
    }

    public void setEdgeWidth(Double edgeWidth) {
        this.edgeWidth = edgeWidth;
    }


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

}
