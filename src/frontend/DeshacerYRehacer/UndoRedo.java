package frontend.DeshacerYRehacer;


import frontend.DeshacerYRehacer.Instructions;

import java.util.ArrayDeque;
import java.util.Deque;


public class UndoRedo {

    private Deque<Instructions> undo = new ArrayDeque<>();

    public void addUndo(Instructions insta){
        undo.push(insta);
    }

    public Instructions undo(){
        return undo.pop();

    }



}
