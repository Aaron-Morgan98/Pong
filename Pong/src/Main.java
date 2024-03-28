public class Main {

    public static int state = 0;
    public static Thread mainThread;
    public static MainMenu menu;
    public static Window window;

    public static void main(String[] args) {
        menu = new MainMenu(); // menu screen

        mainThread = new Thread(menu);
        mainThread.start();
    }

    //change from menu to game
    public static void changeState(int newState) {
        //when start game button is pressed, window closes and game starts
        if (newState == 1 && state == 0) {
            menu.stop();

            window = new Window();
            mainThread = new Thread(window);
            mainThread.start();
        } else if (newState == 0 && state == 1) {
            window.stop();

            menu = new MainMenu();
            mainThread = new Thread(menu);
            mainThread.start();
        } else if (newState == 2) {
            if (window != null)  // end game when a player wins
                window.stop();
            if(menu != null)
                menu.stop();
            state = newState;
        }
    }
}
