package backend.model;



public abstract class Figure {
    private Point centerPoint;

    public Figure(Point centerPoint) {
        this.centerPoint = centerPoint;
    }

    public Point getCenterPoint() {
        return centerPoint;
    }

    public void setCenterPoint(Point centerPoint) {
        this.centerPoint = centerPoint;
    }


    /**
     * mueve la figura en X y en Y actualizando los puntos clave como el centro de la figura y
     * en el caos del rectandulo y el cuadrado las esquinas
     * @param diffX
     * @param diffY
     */
    public abstract void move(double diffX,double diffY);


    /**
     * chequea si el eventpoint que de usa cuando el mouse se mueve
     * esta o no en una figura para actualizar el status Pane
     * @param eventPoint
     * @return
     */
    public abstract boolean isInFigure(Point eventPoint);

    /**
     * cambia el tamaño respecto del centro de las figuras
     * @param amount lo que se agrando o se achica dependiendo si es mayor o menor a 0
     */
    public abstract void changeSize(double amount);
}
