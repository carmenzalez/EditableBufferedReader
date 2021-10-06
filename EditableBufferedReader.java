package LAB1;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author pau
 */
public class EditableBufferedReader extends BufferedReader {
    
    static final int ESC = 27;
    static final int RIGHT = 67;
    static final int LEFT = 68;
    static final int HOME = 72;
    static final int END = 70;
    static final int INSERT = 50;
    static final int DELETE = 51;
    static final int LBRACKET = 91;
    static final int TILDE = 126;
    static final int ENTER = 13;
    
    static final int R_ESC = 1000;
    static final int R_RIGHT = 1001;
    static final int R_LEFT = 1002;
    static final int R_HOME = 1003;
    static final int R_END = 1004;
    static final int R_INSERT = 1005;
    static final int R_DELETE = 1006;
    static final int R_LBRACKET = 1007;

    /**
     * @param args the command line arguments
     */
    public EditableBufferedReader(Reader in) {
        super(in);
    }
    
    public void setRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void unsetRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked </dev/tty"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int read() throws IOException {
        int c;
        try {
            //setRaw();
            c = super.read();
            if (c == ESC) {
                c = super.read();
                if (c == 91) {
                    c = super.read();
                    switch (c) {
                        case RIGHT:
                            //System.out.println("RIGHT");
                            return R_RIGHT;
                            
                        case LEFT:
                            //System.out.println("LEFT");
                            return R_LEFT;
                        
                        case HOME:
                            //System.out.println("HOME");
                            return R_HOME;
                        
                        case END:
                            //System.out.println("END");
                            return R_END;
                            
                        case INSERT:
                            //System.out.println("INSERT");
                            c = super.read();
                            return R_INSERT;
                        
                        case DELETE:
                            //System.out.println("DELETE");
                            c = super.read();
                            return R_DELETE;    
                    }
                }
            }
        } finally {
            //unsetRaw();
        }
        return c;
    }
    
    public String readLine() throws IOException {
        int c;
        Line line = new Line();
        do {
            setRaw();
            c = this.read();
            switch (c) {
                        case R_RIGHT:
                            //System.out.println("RIGHT");
                            line.right();
                            break;
                            
                        case R_LEFT:
                            //System.out.println("LEFT");
                            line.left();
                            break;
                        
                        case R_HOME:
                            //System.out.println("HOME");
                            line.home();
                            break;
                        
                        case R_END:
                            //System.out.println("END");
                            line.end();
                            break;
                            
                        case R_INSERT:
                            //System.out.println("INSERT");
                            line.insert();
                            break;
                        
                        case R_DELETE:
                            //System.out.println("DELETE");
                            line.delete();
                            break;
                        
                        default:
                            System.out.print((char) c);
                            line.addChar(c);
            }
        } while (c != ENTER);
        unsetRaw();
        return line.getLine();
    }
    
    /*public static void main(String[] args) throws IOException {
        int c;
        EditableBufferedReader e = new EditableBufferedReader(new InputStreamReader(System.in));
        c = e.read();
        System.out.println(c);
    }*/
    
}
