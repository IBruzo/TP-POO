package frontend;

import backend.CanvasState;
import backend.model.*;
import frontend.DeshacerYRehacer.Instructions;
import frontend.DeshacerYRehacer.UndoRedo;
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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	FigureButton rectangleButton = new RectangleButton("Rectángulo");
	FigureButton circleButton = new CircleButton("Círculo");
	FigureButton squareButton = new SquareButton("Cuadrado");
	FigureButton ellipseButton = new EllipseButton("Elipse");
	ToggleButton deleteButton = new ToggleButton("Borrar");
	Button expandButton = new Button("Agrandar");
	Button minimizeButton = new Button("Achicar");

	//Slider Grosor de Borde
	Text sliderText = new Text("Borde");
	Slider slider = new Slider(DEF_MIN_SLIDER, DEF_MAX_SLIDER, DEF_INCREMENT_SLIDER);

	//Colorpickers
	ColorPicker edgePicker = new ColorPicker(LINE_COLOR);
	Text fillText = new Text("Relleno");
	ColorPicker fillPicker = new ColorPicker(FILL_COLOR);



	//botonoes barra arriba
	Button undoButton = new Button("Deshacer");
	Button reDoButton= new Button("Rehacer");

	// Dibujar una figura
	Point startPoint;

	// Seleccionar una figura
	FrontFigures selectedFigure;

	// StatusBar
	StatusPane statusPane;

	UndoRedo undoRedo= new UndoRedo();



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
		minimizeButton.setMinWidth(90);
		expandButton.setMinWidth(90);
		minimizeButton.setCursor(Cursor.HAND);
		expandButton.setCursor(Cursor.HAND);

		Button[] toolsUndo = {undoButton, reDoButton};
		ToggleGroup undoTools = new ToggleGroup();
		for (Button undoTool : toolsUndo) {
			undoTool.setMinWidth(90);
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
		buttonsBox.getChildren().addAll(sliderText, slider, edgePicker, fillText, fillPicker);
		buttonsBox.getChildren().add(expandButton);
		buttonsBox.getChildren().add(minimizeButton);


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
			FigureButton[] figureButtons ={ rectangleButton, circleButton, squareButton, ellipseButton};

			FrontFigures newFigure=null;
			for(FigureButton button : figureButtons ){
				if(button.isSelected()){
					newFigure=button.makeFigure(startPoint,endPoint,fillPicker.getValue(),edgePicker.getValue(),slider.getValue());
				}
			}
			if(newFigure!=null)
				canvasState.addFigure(newFigure);

			startPoint = null;
			redrawCanvas();
		});
  //TODO sacar el flag muy imperativo
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

		slider.setOnMouseReleased(e->{
			if(selectedFigure!=null){
				selectedFigure.setEdgeWidth(slider.getValue());
				redrawCanvas();
			}
		});

		expandButton.setOnAction(event ->{
			if(selectedFigure!= null){
				selectedFigure.getFigureBack().changeSize(1.1);
				redrawCanvas();
			}

		});

		minimizeButton.setOnAction(actionEvent -> {
			if(selectedFigure!=null){
				selectedFigure.getFigureBack().changeSize(0.9);
				redrawCanvas();
			}
		});

		undoButton.setOnAction(event->{
			Instructions instruction= undoRedo.undo();

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
