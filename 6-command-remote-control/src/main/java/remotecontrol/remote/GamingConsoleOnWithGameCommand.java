package remotecontrol.remote;

public class GamingConsoleOnWithGameCommand implements Command {
    GamingConsole console;
    public GamingConsoleOnWithGameCommand(GamingConsole console) {
        this.console = console;
    }

    @Override
    public void execute() {
        this.console.on();
        this.console.setVideoGame("The Last Of Us");
    }

}
