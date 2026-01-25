package remotecontrol.remotewithundo;

public class GamingConsole {
    private String currentGame;
    public void on() {
        System.out.println("Console is on");
    }

    public void off() {
        System.out.println("Console is off");
    }

    public void setVideoGame(String videoGame) {
        this.currentGame = videoGame;
        System.out.println("Console is set for: " + videoGame);
    }

    public String getCurrentGame() {
        return currentGame;
    }
}
