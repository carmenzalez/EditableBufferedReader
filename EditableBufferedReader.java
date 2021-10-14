/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package EditableBR;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;


/**
 *
 * @author carmeeeen
 */
public class EditableBufferedReader extends BufferedReader {
    
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
        int c, ch1;
        if ((c = super.read()) != 27) {
            return c;
        }

        switch (c = super.read()) {
            case '[':
                switch (c = super.read()) {
                    case 'C':
                        return Key.R_RIGHT;
                    case 'D':
                        return Key.R_LEFT;
                    case 'H':
                        return Key.R_HOME;
                    case 'F':
                        return Key.R_END;
                    case '3':
                        super.read();
                        return Key.R_DELETE;
                    /*case '1':
                        if((ch1 = super.read()) != '~')
                            return ch1;
                        return R_HOME + c - '1';
                    case '2':
                        if((ch1 = super.read()) != '~')
                            return ch1;
                        return R_HOME + c - '1';
                    case '3':
                        if((ch1 = super.read()) != '~')
                            return ch1;
                        return R_HOME + c - '1';
                    case '4':
                        if((ch1 = super.read()) != '~')
                            return ch1;
                        return R_HOME + c - '1';*/

                    default:
                        return c;
                }
        }
        return c;
    }

    public String readLine() throws IOException {
        int c;
        Line line = new Line();
        setRaw();
        do {
            c = this.read();
            switch (c) {
                case Key.R_RIGHT:
                    line.right();
                    break;

                case Key.R_LEFT:
                    line.left();
                    break;

                case Key.R_HOME:
                    line.home();
                    break;

                case Key.R_END:
                    line.end();
                    break;

                case Key.R_INSERT:
                    line.insert();
                    break;

                case Key.R_DELETE:
                    line.delete();
                    System.out.print("\033[K");
                    break;
                    
                case 127:
                    line.backspace();
                    System.out.print("\033[1K");
                    break;

                case Key.ENTER:
                    break;

                default:
                    line.addChar(c);
            }
            System.out.print("\r" + line.getLine());
            //System.out.print(line.getIndex() + 1); //Visualitzador de posició en temps real
            System.out.print("\033[" + (line.getIndex() + 1) + "G");
            
        } while (c != Key.ENTER);
        unsetRaw();
        return line.getLine();
    }

    
}
