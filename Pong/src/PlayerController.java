import java.awt.event.KeyEvent;

public class PlayerController {
    private Rectangle rectangle;
    private KL keyListener;

    public PlayerController(Rectangle rectangle, KL keyListener){
        this.rectangle = rectangle;
        this.keyListener = keyListener;
    }

    public PlayerController(Rectangle rectangle){
        this.rectangle = rectangle;
        this.keyListener = null;
    }

    public void update(double dt){
        //check for inputs from user (player)
        if(keyListener != null){
            // moving the player paddle up and down
            if(keyListener.isKeyPressed(KeyEvent.VK_DOWN)){
                // makes sure the paddle does not go off-screen (downwards)
                moveDown(dt);
            } else if(keyListener.isKeyPressed(KeyEvent.VK_UP)) {
                // makes sure the paddle does not go off-screen (upwards)
                moveUp(dt);
            }
        }

    }

    public void moveUp(double dt){
        if (rectangle.getY() - Const.PADDLE_SPEED * dt > Const.TOOLBAR_HEIGHT) {
            double newY = this.rectangle.getY() - Const.PADDLE_SPEED * dt;
            this.rectangle.setY(newY);
        }
    }


    public void moveDown(double dt){
        if((rectangle.getY() + Const.PADDLE_SPEED * dt) + rectangle.getHeight() < Const.SCREEN_HEIGHT - Const.INSETS_BOTTOM) {
            double newY = this.rectangle.getY() + Const.PADDLE_SPEED * dt;
            this.rectangle.setY(newY);
        }

    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public KL getKeyListener() {
        return keyListener;
    }

    public void setKeyListener(KL keyListener) {
        this.keyListener = keyListener;
    }
}
