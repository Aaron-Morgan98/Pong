public class Time {

    //return system time in nano time
    public static double timeStarted = System.nanoTime();

    public static double getTime(){
        return (System.nanoTime() - timeStarted) * 1E-9;  // converting to seconds
    }
}
