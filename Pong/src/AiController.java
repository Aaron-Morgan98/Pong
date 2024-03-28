public class AiController {

    private PlayerController playerController;
    private Rectangle ball;


    public AiController(PlayerController playerController, Rectangle ball) {
        this.playerController = playerController;
        this.ball = ball;
    }

    public void update (double dt){
        playerController.update(dt);

        //check to see if ball is above or below ai paddle
        if(ball.getY() < playerController.getRectangle().getY()){
            playerController.moveUp(dt);
        } else if(ball.getY() + ball.getHeight() > playerController.getRectangle().getY() + playerController.getRectangle().getHeight()){
            playerController.moveDown(dt);
        }
    }
}
