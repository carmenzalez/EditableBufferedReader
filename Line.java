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
    private int pos, len;
    
    public Line() {
        //line = new ArrayList<Character>();
        line = new StringBuilder();
    }
    
    public String getLine() {
        //pos = line.length();
        end();
        return line.toString();
    }
    
    public void addChar(int c) {
        if (len != pos) {
            //System.out.print(line.substring(pos, len).toString());
            //System.out.print("\033["+ (len - pos) +"D");
            System.out.append((char) c);
        }
        line.insert(pos, (char) c);
        len = line.length();
        pos++;
    }
    
    public void left() {
        if (pos > 0) {
            pos--;
            System.out.print("\033[1D");
        }
    }
    
    public void right() {
        if (pos < line.length()) {
            pos++;
            System.out.print("\033[1C");
        }
    }
    
    public void home() {
        System.out.print("\033["+ pos +"D");
        pos = 0;
    }
    
    public void end() {
        if (pos != len) {
            System.out.print("\033["+ (len - pos) +"C");
            pos = line.length();
        }
    }
    
    public void insert() {
        
    }
    
    public void delete() {
        if (pos <= len) {
            line.deleteCharAt(pos);
            //line.substring(pos);
            //System.out.print("\033[1D");
            len = line.length() -1;
            System.out.print(line.substring(pos,len).toString());
            //System.out.print("\033[1D");
            //System.out.print(line.substring(pos,len).toString());
        }
    }
}
