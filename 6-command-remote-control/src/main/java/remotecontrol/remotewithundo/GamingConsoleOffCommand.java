package remotecontrol.remotewithundo;

public class GamingConsoleOffCommand implements Command {
    private final GamingConsole console;
    private String currentGame;
    public GamingConsoleOffCommand(GamingConsole console) {
        this.console = console;
    }
    @Override
    public void execute() {
        this.currentGame = console.getCurrentGame();
        console.off();
    }
    @Override
    public void undo() {
        console.on();
        console.setVideoGame(currentGame);
    }
}
