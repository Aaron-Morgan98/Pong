import javax.swing.*;
import java.awt.*;

//creating the window
public class MainMenu extends JFrame implements Runnable{

    private Graphics2D g2;
    private KL keyListener = new KL(); //key presses
    private ML mouseListener = new ML(); //mouse
    private Text startGame, exitGame, title;
    private boolean isRunning = true;

    public MainMenu(){
        this.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
        this.setTitle(Const.SCREEN_TITLE);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when you exit out
        this.addKeyListener(keyListener);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
        //start game/exit game button in the center screen
        this.startGame = new Text("Start Game", new Font("Times New Roman", Font.PLAIN, 40),
                Const.SCREEN_WIDTH / 2.0 - 140.0, Const.SCREEN_HEIGHT / 2.0, Color.WHITE);
        this.exitGame = new Text("Exit", new Font("Times New Roman", Font.PLAIN, 40),
                Const.SCREEN_WIDTH / 2.0 - 80.0, Const.SCREEN_HEIGHT / 2.0 + 60.0, Color.WHITE);
        this.title = new Text("Pong", new Font("Times New Roman", Font.PLAIN, 100),
                Const.SCREEN_WIDTH / 2.0 - 155.0, 200,Color.WHITE);

        g2 = (Graphics2D)getGraphics();
    }

    public void update(double dt) {
        Image dbImage = createImage(getWidth(), getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        g2.drawImage(dbImage,0,0,this);

        //get to see if mouse is hovering over any of the buttons
        if(mouseListener.getMouseX() > startGame.getX() && mouseListener.getMouseX() < startGame.getX() + startGame.getWidth()
                && mouseListener.getMouseY() > startGame.getY() - startGame.getHeight() / 2 &&
                mouseListener.getMouseY() < startGame.getY() + startGame.getHeight() / 2.0) {

            startGame.setColor(new Color(151, 151, 151));
            //check to see if button is pressed
            if(mouseListener.isMousePressed()){
                Main.changeState(1);
            }
        } else {
            startGame.setColor(Color.WHITE);
        }
        //exit button
        if(mouseListener.getMouseX() > exitGame.getX() && mouseListener.getMouseX() < exitGame.getX() + exitGame.getWidth()
                && mouseListener.getMouseY() > exitGame.getY() - exitGame.getHeight() / 2 &&
                mouseListener.getMouseY() < exitGame.getY() + exitGame.getHeight() / 2.0){

            exitGame.setColor(new Color(151, 151, 151));
            //exit game when button pressed
            if(mouseListener.isMousePressed()){
                Main.changeState(2);
            }
        } else {
            exitGame.setColor(Color.WHITE);
        }

    }

    //what is displayed on the screen
    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

        //colour the screen black
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);

        startGame.getText();

        title.draw(g2);
        //buttons
        startGame.draw(g2);
        exitGame.draw(g2);
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
            } catch(Exception e) {
            }
        }
        this.dispose();  //close window when button is pressed

    }
}
