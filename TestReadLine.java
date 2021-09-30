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
public class TestReadLine {
    
    public static void main(String[] args) throws IOException {
        BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in)); 
        String str = null;
        try {
            str = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        System.out.println("\n line is: " + str);
    }
}
