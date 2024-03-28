public class Ball {

    private Rectangle rectangle;
    private Rectangle leftPaddle, rightPaddle;
    private Text leftScoreText, rightScoreText;

    // tracks the balls velocity for both x and y axis
    private double vy = 50.0;
    private double vx = -150.0;

    public Ball(Rectangle rectangle, Rectangle leftPaddle, Rectangle rightPaddle, Text leftScoreText, Text rightScoreText) {
        this.rectangle = rectangle;
        this.leftPaddle = leftPaddle;
        this.rightPaddle = rightPaddle;
        this.leftScoreText = leftScoreText;
        this.rightScoreText = rightScoreText;
    }

    public void update(double dt) {
        double newX = this.rectangle.getX() + vx * dt;
        double newY = this.rectangle.getY() + vy * dt;

        // Collision with left paddle
        if (vx < 0 && newX <= leftPaddle.getX() + leftPaddle.getWidth() &&
                newX + rectangle.getWidth() >= leftPaddle.getX() &&
                newY + rectangle.getHeight() >= leftPaddle.getY() &&
                newY <= leftPaddle.getY() + leftPaddle.getHeight()) {
            // Reverse horizontal velocity only
            vx *= -1;
        }
        // Collision with right paddle
        else if (vx > 0 && newX + rectangle.getWidth() >= rightPaddle.getX() &&
                newX <= rightPaddle.getX() + rightPaddle.getWidth() &&
                newY + rectangle.getHeight() >= rightPaddle.getY() &&
                newY <= rightPaddle.getY() + rightPaddle.getHeight()) {
            // Reverse horizontal velocity only
            vx *= -1;
        }
        // Wall collisions
        if (newY <= Const.TOOLBAR_HEIGHT || newY + rectangle.getHeight() >= Const.SCREEN_HEIGHT) {
            vy *= -1; // Reverse vertical velocity
        }

        // Update the position of the ball
        rectangle.setX(newX);
        rectangle.setY(newY);

        // Check for scoring
        if (newX + rectangle.getWidth() < 0) {
            int rightScore = Integer.parseInt(rightScoreText.getText());
            rightScore++;
            rightScoreText.setText("" + rightScore);
            System.out.println("You have lost a point!");
            // Reset ball position and velocity
            resetBall();
            if(rightScore >= Const.WIN_SCORE){
                Main.changeState(2); // if a player wins, exit game
            }
        } else if (newX > Const.SCREEN_WIDTH) {
            int leftScore = Integer.parseInt(leftScoreText.getText());
            leftScore++;
            leftScoreText.setText(""+ leftScore);
            System.out.println("AI has lost a point!");
            resetBall();
            if(leftScore >= Const.WIN_SCORE){
                Main.changeState(2);
            }
        }
    }


    //resets ball after point is scored
    private void resetBall() {
        // Reset ball to the center of the screen
        rectangle.setX(Const.SCREEN_WIDTH / 2 - rectangle.getWidth() / 2);
        rectangle.setY(Const.SCREEN_HEIGHT / 2 - rectangle.getHeight() / 2);

        int rightScore = Integer.parseInt(rightScoreText.getText());
        int leftScore = Integer.parseInt(leftScoreText.getText());
        // Reset velocity send ball towards the player who lost a point
        if(rightScore > leftScore){
            vx = -150.0;
        } else {
            vx = 150.0;
        }
        vy = 50.0;


    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public Rectangle getLeftPaddle() {
        return leftPaddle;
    }

    public void setLeftPaddle(Rectangle leftPaddle) {
        this.leftPaddle = leftPaddle;
    }

    public Rectangle getRightPaddle() {
        return rightPaddle;
    }

    public void setRightPaddle(Rectangle rightPaddle) {
        this.rightPaddle = rightPaddle;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }
}
