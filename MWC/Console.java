package MWC;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author pau
 */
public class Console implements Observer {
    
    private Line line;
    
    public Console(Line linea) {
        line = linea;
    }

    @Override
    public void update(Observable obs, Object obj) {
        int x = (int) obj;
        switch(x) {
            case Key.LEFT:
                System.out.print("\033[1D");
                break;
                
            case Key.RIGHT:
                System.out.print("\033[1C");
                break;
                
            case Key.HOME:
                System.out.print("\033[1G");
                break;
                
            case Key.END:
                System.out.print("\033[" + (line.getIndex() + 1) + "G");
                break;
                
            case Key.CHAR:
                System.out.print(line.getLine().charAt(line.getIndex() - 1));
                
            case Key.DELETE:
                System.out.print("\033[P");
                break;
                
            case Key.BACKSPACE:
                System.out.print("\033[1D\033[P");
                break;
        }
    }
    
}
