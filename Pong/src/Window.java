import javax.swing.*;
import java.awt.*;

//creating the window
public class Window extends JFrame implements Runnable{

    private Graphics2D g2;
    private KL keyListener = new KL();
    private Rectangle playerOne, ai, ballRectangle;
    private PlayerController playerController;
    private AiController aiController;
    private Ball ball;
    private Text leftScoreText, rightScoreText;

    private boolean isRunning = true;




    public Window(){
        this.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        this.setTitle(Const.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when you exit out
        this.addKeyListener(keyListener);
        Const.TOOLBAR_HEIGHT = this.getInsets().top;  // makes sure the toolbar at the top is not included in screen size
        Const.INSETS_BOTTOM = this.getInsets().bottom;
        g2 = (Graphics2D)this.getGraphics(); //casting

        //scores
        leftScoreText = new Text(0, new Font("Times New Roman",Font.PLAIN,Const.TEXT_SIZE),
                Const.TEXT_X_POS,Const.TEXT_Y_POS);
        rightScoreText = new Text(0, new Font("Times New Roman",Font.PLAIN,Const.TEXT_SIZE),
                Const.SCREEN_WIDTH - Const.TEXT_X_POS - 16,Const.TEXT_Y_POS);

        //creating the player shapes/ball
        playerOne = new Rectangle(Const.HZ_PADDING,40,Const.PADDLE_WIDTH,
                Const.PADDLE_HEIGHT,Const.PADDLE_COLOR);
        playerController = new PlayerController(playerOne,keyListener);


        ai = new Rectangle(Const.SCREEN_WIDTH - Const.PADDLE_WIDTH - Const.HZ_PADDING,40,Const.PADDLE_WIDTH,
                Const.PADDLE_HEIGHT,Const.PADDLE_COLOR);

        ballRectangle = new Rectangle(Const.SCREEN_WIDTH / 2, Const.SCREEN_HEIGHT / 2,
                Const.BALL_WIDTH,Const.BALL_WIDTH,Const.PADDLE_COLOR);
        ball = new Ball(ballRectangle, playerOne, ai,leftScoreText,rightScoreText);


        aiController = new AiController(new PlayerController(ai), ballRectangle);



    }

    public void update(double dt) {
        //make the movement less laggy
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        playerController.update(dt);
        aiController.update(dt);
        ball.update(dt);

//        System.out.println("" + dt + " seconds passed since the last frame"); // give us the time it takes to complete the last frame
//        System.out.println(1 / dt + " fps");
    }

    //what is displayed on the screen
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        //colour the screen black
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);

        leftScoreText.draw(g2);
        rightScoreText.draw(g2);

        playerOne.draw(g2);
        ai.draw(g2);
        ballRectangle.draw(g2);
    }

    public void stop(){
        isRunning = false;
    }

    //loop ran by our thread
    public void run(){
        double lastFrameTime = 0.0;
        while(isRunning){
            double time = Time.getTime();
            double deltaTime = time - lastFrameTime;
            lastFrameTime = time;

            update(deltaTime);

            //limit to 30 fps
            try {
                Thread.sleep(60);
            } catch(Exception e){
            }
        }
        this.dispose();

    }

}
