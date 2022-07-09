package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.Instruction.*;
import frontend.FrontFigure.*;
import frontend.figureButtons.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.*;


public class PaintPane extends BorderPane {

	// BackEnd
	private final CanvasState canvasState;

	// Canvas y relacionados
	private final Canvas canvas = new Canvas(800, 600);
	private final GraphicsContext gc = canvas.getGraphicsContext2D();
	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color FILL_COLOR = Color.YELLOW;
	private static final int DEF_MIN_SLIDER = 1;
	private static final int DEF_MAX_SLIDER = 50;
	private static final int DEF_INCREMENT_SLIDER = 1;

	// Botones Barra Izquierda
	private final ToggleButton selectionButton = new ToggleButton("Seleccionar");
	private final FigureButton rectangleButton = new RectangleButton("Rectángulo");
	private final FigureButton circleButton = new CircleButton("Círculo");
	private final FigureButton squareButton = new SquareButton("Cuadrado");
	private final FigureButton ellipseButton = new EllipseButton("Elipse");
	private final ToggleButton deleteButton = new ToggleButton("Borrar");
	private final Button expandButton = new Button("Agrandar");
	private final Button minimizeButton = new Button("Achicar");

	//constantes ligadas al achicar y agrandar
	private  final Text sizeText = new Text("Cambiar Tamaño");
	private static final double AGRANDAR_10=1.1;
	private static final double ACHICAR_10=0.9;

	//Slider Grosor de Borde
	private  final Text sliderText = new Text("Borde");
	private final Slider slider = new Slider(DEF_MIN_SLIDER, DEF_MAX_SLIDER, DEF_INCREMENT_SLIDER);

	//Colorpickers
	private final ColorPicker edgePicker = new ColorPicker(LINE_COLOR);
	private final Text fillText = new Text("Relleno");
	private final ColorPicker fillPicker = new ColorPicker(FILL_COLOR);

	//botonoes barra arriba
	private final Button undoButton = new Button("Deshacer");
	private final Button reDoButton= new Button("Rehacer");

	// Dibujar una figura
	private Point startPoint;

	// Seleccionar una figura
	private FrontFigures selectedFigure;

	// StatusBar
	private StatusPane statusPane;

	private UndoRedo undoRedo= new UndoRedo();


    //maneja lo que es botones, acciones y layout del canvas
	public PaintPane(CanvasState canvasState, StatusPane statusPane) {

		//esta primera parte es el layout de los botnes y las boxes
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, deleteButton, rectangleButton,  squareButton, ellipseButton,circleButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}
		minimizeButton.setMinWidth(90);
		expandButton.setMinWidth(90);
		minimizeButton.setCursor(Cursor.HAND);
		expandButton.setCursor(Cursor.HAND);

		Button[] toolsUndo = {undoButton, reDoButton};
		for (Button undoTool : toolsUndo) {
			undoTool.setMinWidth(90);
			undoTool.setAlignment(Pos.CENTER);
			undoTool.setCursor(Cursor.HAND);
		}

		HBox undoBar = new HBox(10);
		undoBar.setAlignment(Pos.CENTER);
		undoBar.setSpacing(5);
		undoBar.setStyle("-fx-background-color: #999");
		undoBar.getChildren().addAll(undoRedo.getUndoLabel(),undoButton,reDoButton,undoRedo.getRedoLabel());
		undoBar.setPadding(new Insets(4));
		undoBar.setPrefHeight(30);
		gc.setLineWidth(1);

		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(6));
		buttonsBox.setStyle("-fx-background-color: #999");
		buttonsBox.setPrefWidth(100);
		gc.setLineWidth(1);


		slider.setShowTickMarks(true);
		slider.setShowTickLabels(true);
		slider.setMajorTickUnit(25);
		slider.setBlockIncrement(5);
		slider.setCursor(Cursor.HAND);
		edgePicker.setCursor(Cursor.HAND);
		fillText.setCursor(Cursor.HAND);

		buttonsBox.getChildren().addAll(sliderText, slider, edgePicker, fillText, fillPicker,sizeText,expandButton,minimizeButton);

		//actualiza el startpoint cuando clickamos en el canvas
		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		//cuando es seleccionado un boton de figuras y se suelta el mouse luego de un click agrega las figuras a la lista de canvas state
		// y actualiza el canvas
		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			//si el endpoint es menor o igual al startpoint no se crea la figura
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY() || startPoint.equals(endPoint)) {
				statusPane.updateStatus("Porfavor crear las figuras de arriba a la izquerda hacia abajo a la derecha");
				return ;
			}
			FigureButton[] figureButtons ={ rectangleButton,  squareButton, ellipseButton,circleButton};

			FrontFigures newFigure=null;
			for(FigureButton button : figureButtons ){
				if(button.isSelected()){
					newFigure=button.makeFigure(startPoint,endPoint,fillPicker.getValue(),edgePicker.getValue(),slider.getValue(),canvasState.size());
					undoRedo.addUndo(new Dibujar(newFigure,canvasState));
				}
			}
			if(newFigure!=null) {
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				canvasState.addFigure(newFigure);
			}

			startPoint = null;
			redrawCanvas();
		});

		// actualiza el satuspane(la barra azul de abajo) con figuras seleccionadas, hover sobre una figura y el punto en el que se encuantra el cursor
		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(FrontFigures figure : canvasState.figures()) {
				if(figure.getFigureBack().isInFigure(eventPoint)) {
					found = true;
					label.append(figure.getFigureBack());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		//cuando esta seleccionado el boton de select actualiza el paint pane y la variable selected figure para indicar que
		// tal figura fue seleccionada y usar la variable con los otros botones
		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (FrontFigures figure : canvasState.figures()) {
					if(figure.getFigureBack().isInFigure(eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.getFigureBack());
					}
				}
				if (found) {
					statusPane.updateStatus(label.toString());
				} else {
					selectedFigure = null;
					statusPane.updateStatus("Ninguna figura encontrada");
				}
				redrawCanvas();
			}
		});

		//move la figura seleccinada por el canvas
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(selectedFigure!=null)
				     selectedFigure.getFigureBack().move(diffX,diffY);
				startPoint.movePoint(diffX,diffY);
				redrawCanvas();
			}
		});


		//las siguientes funciones cambian las figuras de front y sus valores
		// tambien si la instruccion se puede deshacer se guarda en el stack de undo y borrra el redo

		fillPicker.setOnAction(e->{
			if(selectedFigure!=null) {
				undoRedo.addUndo(new FillColor( selectedFigure,selectedFigure.getFillColor(),fillPicker.getValue()));
				selectedFigure.setFillColor(fillPicker.getValue());
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		edgePicker.setOnAction(e->{
			if(selectedFigure!=null){
				undoRedo.addUndo(new EdgeColor(selectedFigure,selectedFigure.getEdgeColor(),edgePicker.getValue()));
				selectedFigure.setEdgeColor(edgePicker.getValue());
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		slider.setOnMouseReleased(e->{
			if(selectedFigure!=null){
				selectedFigure.setEdgeWidth(slider.getValue());
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		expandButton.setOnAction(event ->{
			if(selectedFigure!= null){
				undoRedo.addUndo(new Agrandar(selectedFigure));
				selectedFigure.getFigureBack().changeSize(AGRANDAR_10);
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}

		});

		minimizeButton.setOnAction(actionEvent -> {
			if(selectedFigure!=null){
				undoRedo.addUndo(new Achicar(selectedFigure));
				selectedFigure.getFigureBack().changeSize(ACHICAR_10);
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		undoButton.setOnAction(event->{
			if(undoRedo.canUndo()) {
				Instruction instruction = undoRedo.undo();
				instruction.undo();
				undoRedo.changeLabels();
				redrawCanvas();
			}

		});

		reDoButton.setOnAction(event->{
			if(undoRedo.canRedo()) {
				Instruction instruction = undoRedo.redo();
				instruction.redo();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		deleteButton.setOnAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				undoRedo.addUndo(new Borrar(selectedFigure,canvasState));
				selectedFigure = null;
				undoRedo.getRedo().clear();
				undoRedo.changeLabels();
				redrawCanvas();
			}
		});

		setTop(undoBar);
		setLeft(buttonsBox);
		setRight(canvas);
	}

	/**
	 * esta funcion se usa para poder actualizar el canvas cuando se ejecuta alguna de las instruccion o acciones
	 */
	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (FrontFigures figure : canvasState.figures()) {
				if (figure == selectedFigure) {
					figure.draw(gc, Color.RED);
				} else {
					figure.draw(gc);
				}
		}
	}

}
