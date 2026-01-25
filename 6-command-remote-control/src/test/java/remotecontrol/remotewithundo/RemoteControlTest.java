package remotecontrol.remotewithundo;

import org.junit.jupiter.api.Test;

public class RemoteControlTest {
    @Test
    public void addGamingConsoleToRemoteControl() {
        GamingConsole gamingConsole = new GamingConsole();
        Command gamingConsoleOnWithGameCommand = new GamingConsoleOnWithGameCommand(gamingConsole);
        Command gamingConsoleOffCommand = new GamingConsoleOffCommand(gamingConsole);

        RemoteControlWithUndo remoteControl = new RemoteControlWithUndo();
        remoteControl.setCommand(0, gamingConsoleOnWithGameCommand, gamingConsoleOffCommand);
        remoteControl.onButtonWasPushed(0);
        remoteControl.offButtonWasPushed(0);
        remoteControl.undoButtonWasPushed();
    }

}
