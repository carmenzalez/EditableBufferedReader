package LAB1;


import java.io.*;

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

    /**
     * @param args the command line arguments
     */
    public EditableBufferedReader(Reader in) {
        super(in);
    }
    
    public void setRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty -echo raw"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void unsetRaw() {
        String[] cmd = {"/bin/sh", "-c", "stty echo cooked"};
        try {
            Runtime.getRuntime().exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public int read() throws IOException {
        int c;
        try {
            setRaw();
            c = super.read();
            if (c == ESC) {
                c = super.read();
                if (c == 91) {
                    c = super.read();
                    switch (c) {
                        case RIGHT:
                            System.out.println("RIGHT");
                            break;
                            
                        case LEFT:
                            System.out.println("LEFT");
                            break;
                        
                        case HOME:
                            System.out.println("HOME");
                            break;
                        
                        case END:
                            System.out.println("END");
                            break;
                            
                        case INSERT:
                            System.out.println("INSERT");
                            break;
                        
                        case DELETE:
                            System.out.println("DELETE");
                            break;    
                    }
                }
            }
        } finally {
            unsetRaw();
        }   
        return c;
    }
    
    public static void main(String[] args) throws IOException {
        int c;
        EditableBufferedReader e = new EditableBufferedReader(new InputStreamReader(System.in));
        c = e.read();
        System.out.println(c);
    }
    
}
