package MWC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 *
 * @author pau
 */
public class EditableBufferedReader extends BufferedReader {
    
    protected Line line;
    private Console console;
    
    public EditableBufferedReader(Reader in) {
        super(in);
        line = new Line();
        console = new Console(line);
        line.addObserver(console);
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
                    break;
                    
                case Key.BACKSPACE:
                    line.backspace();
                    break;

                case Key.ENTER:
                    break;

                default:
                    line.addChar(c);
            }
        } while (c != Key.ENTER);
        unsetRaw();
        return line.getLine();
    }
    
}
