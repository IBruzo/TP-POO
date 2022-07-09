package frontend.Instruction;


import javafx.geometry.Pos;
import javafx.scene.control.Label;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.NoSuchElementException;


public class UndoRedo {

    private Deque<Instruction> undo = new ArrayDeque<>();
    private Deque<Instruction> redo = new ArrayDeque<>();
    private Label undoLabel= new Label("0");
    private Label redoLabel= new Label("0");

    public UndoRedo() {
        //arregla el alineamiento de los botones con respecto de las label(si el texto era muy largo se corrian los botones)
        undoLabel.setAlignment(Pos.CENTER_RIGHT);
        undoLabel.setMinWidth(300);
        redoLabel.setMinWidth(300);
        redoLabel.setAlignment(Pos.CENTER_LEFT);
    }

    /**
     * cada ves q se ejecuta una instruccion en el PaintPane se gurada en el stack de undo
     * para poder revertirla y pasarsela al stack de redo
     * @param insta instruccion que se uso en la figura
     */
    public void addUndo(Instruction insta){
        undo.push(insta);
    }

// estas funciones se pasan entre si  las instrucciones
    public Instruction undo(){
        if(canUndo()) {
            Instruction aux = undo.pop();
            redo.push(aux);
            return aux;
        }
        else
            throw new NoSuchElementException("No hay operaciones que deshacer");
    }

    public Instruction redo(){
        if(canRedo()) {
            Instruction aux = redo.pop();
            undo.push(aux);
            return aux;
        }
        else
            throw new NoSuchElementException("No hay operaciones que rehacer");
    }

    /**
     * actualiza los label para avisarle al usuario si puede deshacer y rehacer.
     * tambien le dice que instruccion va a reacher o deshacer
     */
    public void changeLabels(){
        undoLabel.setText(String.format("%s\t  %d",canUndo()? undo.peek():"",undo.size()));
        redoLabel.setText(String.format("%d\t%s",redo.size(),canRedo()? redo.peek():""));
    }


    //chequeo para ver si se precionan los botones cuando no hay nada que dehacer o reacher
    private boolean canUndo(){
        return undo.size() != 0;
    }
    private boolean canRedo(){
        return redo.size() != 0;
    }

    //getters

    public Deque<Instruction> getRedo() {
        return redo;
    }

    public Label getUndoLabel() {
        return undoLabel;
    }

    public Label getRedoLabel() {
        return redoLabel;
    }


}
