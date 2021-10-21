package MWC;

import java.util.Observable;

/**
 *
 * @author pau
 */
public class Line extends Observable {
    
    protected StringBuffer line;
    private int index;
    private boolean insertMode;

    public Line() {
        line = new StringBuffer();
        insertMode = true;
    }

    public String getLine() {
        return line.toString();
    }

    public int getIndex() {
        return index;
    }

    public void addChar(int c) {
        if (insertMode || (!insertMode && index >= line.length())) {
            line.insert(index, (char) c);
            index++;
        } else {
            line.setCharAt(index, (char) c);
            index++;
        }
        this.setChanged();
        this.notifyObservers(Key.CHAR);
    }

    public void left() {
        if (index > 0) {
            index--;
        }
        this.setChanged();
        this.notifyObservers(Key.LEFT);
    }

    public void right() {
        if (index < line.length()) {
            index++;
        }
        this.setChanged();
        this.notifyObservers(Key.RIGHT);
    }

    public void home() {
        index = 0;
        this.setChanged();
        this.notifyObservers(Key.HOME);
    }

    public void end() {
        if (index != line.length()) {
            index = line.length();
        }
        this.setChanged();
        this.notifyObservers(Key.END);
    }

    public void insert() {
        insertMode = !insertMode;
    }

    public void delete() {
        if (index < line.length()) {
            line.deleteCharAt(index);
        }
        this.setChanged();
        this.notifyObservers(Key.DELETE);
    }
    
    public void backspace() {
        if(index > 0) {
            index--;
            line.deleteCharAt(index);
        }
        this.setChanged();
        this.notifyObservers(Key.BACKSPACE);
    }
}
