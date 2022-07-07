package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.FrontFigure.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Slider;
import javafx.scene.text.*;

//TODO chequear los ifs

public class PaintPane extends BorderPane {



	// BackEnd
	CanvasState canvasState;

	// Canvas y relacionados
	Canvas canvas = new Canvas(800, 600);
	GraphicsContext gc = canvas.getGraphicsContext2D();
	private static final Color LINE_COLOR = Color.BLACK;
	private static final Color FILL_COLOR = Color.YELLOW;
	private static final int DEF_MIN_SLIDER = 1;
	private static final int DEF_MAX_SLIDER = 50;
	private static final int DEF_INCREMENT_SLIDER = 1;

	// Botones Barra Izquierda
	ToggleButton selectionButton = new ToggleButton("Seleccionar");
	ToggleButton rectangleButton = new ToggleButton("Rectángulo");
	ToggleButton circleButton = new ToggleButton("Círculo");
	ToggleButton squareButton = new ToggleButton("Cuadrado");
	ToggleButton ellipseButton = new ToggleButton("Elipse");
	ToggleButton deleteButton = new ToggleButton("Borrar");

	//Slider Grosor de Borde
	Text sliderText = new Text("Borde");
	Slider slider = new Slider(DEF_MIN_SLIDER, DEF_MAX_SLIDER, DEF_INCREMENT_SLIDER);

	//Colorpickers
	ColorPicker edgePicker = new ColorPicker(LINE_COLOR);
	Text fillText = new Text("Relleno");
	ColorPicker fillPicker = new ColorPicker(FILL_COLOR);



	//botonoes barra arriba
	ToggleButton undoButton = new ToggleButton("Deshacer");
	ToggleButton reDoButton= new ToggleButton("Rehacer");

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	FrontFigures selectedFigure;

	// StatusBar
	StatusPane statusPane;



	public PaintPane(CanvasState canvasState, StatusPane statusPane) {
		this.canvasState = canvasState;
		this.statusPane = statusPane;
		ToggleButton[] toolsArr = {selectionButton, rectangleButton, circleButton, squareButton, ellipseButton, deleteButton};
		ToggleGroup tools = new ToggleGroup();
		for (ToggleButton tool : toolsArr) {
			tool.setMinWidth(90);
			tool.setToggleGroup(tools);
			tool.setCursor(Cursor.HAND);
		}

		ToggleButton[] toolsUndo = {undoButton, reDoButton};
		ToggleGroup undoTools = new ToggleGroup();
		for (ToggleButton undoTool : toolsUndo) {
			undoTool.setMinWidth(90);
			undoTool.setToggleGroup(undoTools);
			undoTool.setCursor(Cursor.HAND);
		}

		HBox undoBar = new HBox(10);
		undoBar.setAlignment(Pos.CENTER);
		undoBar.setStyle("-fx-background-color: #999");
		undoBar.getChildren().addAll(toolsUndo);
		undoBar.setPadding(new Insets(2));
		undoBar.setPrefHeight(30);
		gc.setLineWidth(1);

		VBox buttonsBox = new VBox(10);
		buttonsBox.getChildren().addAll(toolsArr);
		buttonsBox.setPadding(new Insets(5));
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
		buttonsBox.getChildren().addAll(sliderText, slider, edgePicker, fillText, fillPicker);



		canvas.setOnMousePressed(event -> {
			startPoint = new Point(event.getX(), event.getY());
		});

		canvas.setOnMouseReleased(event -> {
			Point endPoint = new Point(event.getX(), event.getY());
			if(startPoint == null) {
				return ;
			}
			if(endPoint.getX() < startPoint.getX() || endPoint.getY() < startPoint.getY()) {
				return ;
			}
			FrontFigures newFigure = null;
			if(rectangleButton.isSelected()) {
				newFigure = new RectangleFront(startPoint, endPoint,fillPicker.getValue(),edgePicker.getValue(),slider.getValue());
			}
			else if(circleButton.isSelected()) {
				double circleRadius = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new CircleFront(startPoint, circleRadius,fillPicker.getValue(),edgePicker.getValue(),slider.getValue());
			} else if(squareButton.isSelected()) {
				double size = Math.abs(endPoint.getX() - startPoint.getX());
				newFigure = new SquareFront(startPoint, size,fillPicker.getValue(),edgePicker.getValue(),slider.getValue());
			} else if(ellipseButton.isSelected()) {
				Point centerPoint = new Point(Math.abs(endPoint.getX() + startPoint.getX()) / 2, (Math.abs((endPoint.getY() + startPoint.getY())) / 2));
				double sMayorAxis = Math.abs(endPoint.getX() - startPoint.getX());
				double sMinorAxis = Math.abs(endPoint.getY() - startPoint.getY());
				newFigure = new EllipseFront(centerPoint, sMayorAxis, sMinorAxis,fillPicker.getValue(),edgePicker.getValue(),slider.getValue());
			} else {
				return ;
			}
			canvasState.addFigure(newFigure);
			startPoint = null;
			redrawCanvas();
		});

		canvas.setOnMouseMoved(event -> {
			Point eventPoint = new Point(event.getX(), event.getY());
			boolean found = false;
			StringBuilder label = new StringBuilder();
			for(FrontFigures figure : canvasState.figures()) {
				if(figure.getFigureBack().isInFigure(eventPoint)) {
					found = true;
					label.append(figure.toString());
				}
			}
			if(found) {
				statusPane.updateStatus(label.toString());
			} else {
				statusPane.updateStatus(eventPoint.toString());
			}
		});

		canvas.setOnMouseClicked(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				boolean found = false;
				StringBuilder label = new StringBuilder("Se seleccionó: ");
				for (FrontFigures figure : canvasState.figures()) {
					if(figure.getFigureBack().isInFigure(eventPoint)) {
						found = true;
						selectedFigure = figure;
						label.append(figure.toString());
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
       //mueve las figuras cuando drageas, medio raro las mueve
		canvas.setOnMouseDragged(event -> {
			if(selectionButton.isSelected()) {
				Point eventPoint = new Point(event.getX(), event.getY());
				double diffX = (eventPoint.getX() - startPoint.getX()) / 100;
				double diffY = (eventPoint.getY() - startPoint.getY()) / 100;
				if(selectedFigure!=null)
				     selectedFigure.getFigureBack().move(diffX,diffY);
				redrawCanvas();
			}
		});

		fillPicker.setOnAction(e->{
			if(selectedFigure!=null) {
				selectedFigure.setFillColor(fillPicker.getValue());
				redrawCanvas();
			}
		});

		edgePicker.setOnAction(e->{
			if(selectedFigure!=null){
				selectedFigure.setEdgeColor(edgePicker.getValue());
				redrawCanvas();
			}
		});



		deleteButton.setOnAction(event -> {
			if (selectedFigure != null) {
				canvasState.deleteFigure(selectedFigure);
				selectedFigure = null;
				redrawCanvas();
			}
		});

		setTop(undoBar);
		setLeft(buttonsBox);
		setRight(canvas);
	}

	void redrawCanvas() {
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		for (FrontFigures figure : canvasState.figures()) {
			if (figure == selectedFigure) {
				figure.draw(gc,Color.RED);
			} else {
				figure.draw(gc);
			}
		}
	}

}
