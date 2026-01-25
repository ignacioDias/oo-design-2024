package remotecontrol.remote;

public class GamingConsole {
    String videoGame;
    public GamingConsole() {
    }

    public void on() {
        System.out.println("Console is on");
    }

    public void off() {
        System.out.println("Console is off");
    }

    public void setVideoGame(String videoGame) {
        this.videoGame = videoGame;
        System.out.println("Console is set for: " + videoGame);
    }

}
