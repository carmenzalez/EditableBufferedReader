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
    private int pos;
    
    public Line() {
        //line = new ArrayList<Character>();
        line = new StringBuilder();
    }
    
    public String getLine() {
        pos = line.length();
        return line.toString();
    }
    
    public void addChar(int c) {
        line.insert(pos, (char) c);
        pos++;
    }
    
    public void left() {
        if (pos > 0) {
            pos--;
        }
        System.out.print("\b");
    }
    
    public void right() {
        if (pos < line.length()) {
            pos++;
        }
    }
    
    public void home() {
        pos = 0;
    }
    
    public void end() {
        pos = line.length();
    }
    
    public void insert() {
        
    }
    
    public void delete() {
        if (pos < line.length()) {
            line.deleteCharAt(pos);
        }
    }
}
