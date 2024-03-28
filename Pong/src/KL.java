import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//user inputs
public class KL implements KeyListener {

    private boolean keyPressed[] = new boolean[128];

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //every time a user presses a key, this method is called
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = true;

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        keyPressed[keyEvent.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int keyCode){
        return keyPressed[keyCode];
    }
}
