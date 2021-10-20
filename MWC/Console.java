package MWC;

import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author pau
 */
public class Console implements Observer {

    @Override
    public void update(Observable obs, Object obj) {
        int x = (int) obj;
        switch(x) {
            case Key.LEFT:
                
            case Key.RIGHT:
                
            case Key.HOME:
                
            case Key.END:
                
            case Key.INSERT:
                
            case Key.CHAR:
            case Key.DELETE:
            case Key.BACKSPACE:
        }
    }
    
}
