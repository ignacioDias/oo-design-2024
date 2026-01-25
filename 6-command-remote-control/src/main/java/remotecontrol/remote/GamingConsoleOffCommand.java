package remotecontrol.remote;

public class GamingConsoleOffCommand implements Command {
    GamingConsole console;
    public GamingConsoleOffCommand(GamingConsole console) {
        this.console = console;
    }
    @Override
    public void execute() {
        this.console.off();
    }
}
