package LAB1;

//import java.util.ArrayList;
/**
 *
 * @author pau
 */
public class Line {

    //public String line;
    //public ArrayList<Character> line;
    private StringBuilder line;
    private int index, len;
    private boolean insertMode;

    public Line() {
        //line = new ArrayList<Character>();
        line = new StringBuilder();
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
        line.insert(index, (char) c);
        len = line.length();
        index++;
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

    }

    public void delete() {
        if (index < line.length()) {
            line.deleteCharAt(index);
            len = line.length() - 1;
        }
    }
    
    public void backspace() {
        if(index > 0) {
            index--;
            line.deleteCharAt(index);
        }
    }
}
