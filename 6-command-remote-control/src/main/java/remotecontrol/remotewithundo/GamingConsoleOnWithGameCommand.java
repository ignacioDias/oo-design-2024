package remotecontrol.remotewithundo;

public class GamingConsoleOnWithGameCommand implements Command {
    private final GamingConsole console;

    public GamingConsoleOnWithGameCommand(GamingConsole console) {
        this.console = console;
    }

    @Override
    public void execute() {
        console.on();
        console.setVideoGame("The Last Of Us");
    }

    @Override
    public void undo() {
        console.off();
    }
}
