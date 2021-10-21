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
    
    Line line;

    /**
     * @param args the command line arguments
     */
    public EditableBufferedReader(Reader in) {
        super(in);
        line = new Line();
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
        if ((c = super.read()) != Key.ESC) {
            return c;
        }

        switch (c = super.read()) {
            case '[':
                switch (c = super.read()) {
                    case 'C':
                        return Key.RIGHT;
                    case 'D':
                        return Key.LEFT;
                    case 'H':
                        return Key.HOME;
                    case 'F':
                        return Key.END;
                    case '1':
                    case '2':
                    case '3':
                    case '4':
                        if((ch1 = super.read()) != '~')
                            return ch1;
                        return Key.HOME + c - '1';
                    default:
                        return c;
                }
        }
        return c;
    }

    public String readLine() throws IOException {
        int c;
        setRaw();
        do {
            c = this.read();
            switch (c) {
                case Key.RIGHT:
                    line.right();
                    break;

                case Key.LEFT:
                    line.left();
                    break;

                case Key.HOME:
                    line.home();
                    break;

                case Key.END:
                    line.end();
                    break;

                case Key.INSERT:
                    line.insert();
                    break;

                case Key.DELETE:
                    line.delete();
                    System.out.print("\033[2K");
                    break;
                    
                case Key.BACKSPACE:
                    line.backspace();
                    System.out.print("\033[2K");
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
