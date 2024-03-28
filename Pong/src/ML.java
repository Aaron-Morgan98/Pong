import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ML extends MouseAdapter implements MouseMotionListener {

    private boolean isPressed = false;
    public double x,y = 0.0;

    @Override
    public void mousePressed(MouseEvent e) {
        isPressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isPressed = false;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX(); //gives position of mouse x-axis
        this.y =e.getY();
    }

    public boolean isMousePressed() {
        return isPressed;
    }

    public void setMousedPressed(boolean pressed) {
        isPressed = pressed;
    }

    public double getMouseX() {
        return x;
    }

    public void setMouseX(double x) {
        this.x = x;
    }

    public double getMouseY() {
        return y;
    }

    public void setMouseY(double y) {
        this.y = y;
    }


}
