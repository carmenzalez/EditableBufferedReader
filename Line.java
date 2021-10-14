package LAB1;

//import java.util.ArrayList;
/**
 *
 * @author pau
 */
public class Line {

    private StringBuffer line;
    private int index;
    private boolean insertMode;

    public Line() {
        line = new StringBuffer();
        insertMode = true;
    }

    public String getLine() {
        //pos = line.length();
        //end();
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
    }

    public void left() {
        if (index > 0) {
            index--;
        }
    }

    public void right() {
        if (index < line.length()) {
            index++;
        }
    }

    public void home() {
        index = 0;
    }

    public void end() {
        if (index != line.length()) {
            index = line.length();
        }
    }

    public void insert() {
        insertMode = !insertMode;
    }

    public void delete() {
        if (index < line.length()) {
            line.deleteCharAt(index);
        }
    }
    
    public void backspace() {
        if(index > 0) {
            index--;
            line.deleteCharAt(index);
        }
    }
}
