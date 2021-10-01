/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Editable;

/**
 *
 * @author carmeeeen
 */

// Programar la classe Line que mantingui l’estat de la línia en edició amb els seus corresponents mètodes.

public class Line {
    
    public String line;
    int pos, len;
    
    public Line() {
        line = "";
    }
    
    /*public int getPos(){
        return this.pos;    
    }*/
    
    public String getLine() {
        return line;
    }
    public void addChar(int c) {
        line = line + (char) c;
    }
    public void left() {
        if (this.pos > 0) {
            this.pos--;
        }

    }
    public void right() {
        if (this.pos < this.line.length()) {
            this.pos++;
        }
    }
    
    public void home() {
        this.pos = 0;
    }
    
    public void end() {
        this.pos = this.line.length();
    }
    
    public void insert() {
        // ins: commuta mode inserció/sobre-escriptura 
        
       
    }
    
    public void delete() {
        if(this.pos < this.line.length()){
            this.line.substring(this.pos);
            len = this.line.length() - 1;
        }
    }
}
