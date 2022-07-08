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
        undoLabel.setAlignment(Pos.CENTER_RIGHT);
        undoLabel.setMinWidth(300);
        redoLabel.setMinWidth(300);
        redoLabel.setAlignment(Pos.CENTER_LEFT);
    }

    public void addUndo(Instruction insta){
        undo.push(insta);
    }

    public Deque<Instruction> getRedo() {
        return redo;
    }

    public Deque<Instruction> getUndo() {
        return undo;
    }

    public Instruction undo(){
        Instruction aux= undo.pop();
            redo.push(aux);
            return aux;
    }

    public Instruction redo(){
        Instruction aux = redo.pop();
        undo.push(aux);
        return aux;
    }

    public void changeLabels(){
        undoLabel.setText(String.format("%s\t%d",canUndo()? undo.peek():"",undo.size()));
        redoLabel.setText(String.format("%d\t%s",redo.size(),canRedo()? redo.peek():""));
    }

    public Label getUndoLabel() {
        return undoLabel;
    }

    public Label getRedoLabel() {
        return redoLabel;
    }

    public boolean canUndo(){
        return undo.size()!=0;
    }
    public boolean canRedo(){
        return redo.size()!=0;
    }




}
