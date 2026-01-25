package remotecontrol.remotewithundo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RemoteControlWithMultipleUndoTest {

    @Test
    void constructorWithInvalidLimitThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new RemoteControlWithMultipleUndo(0));
        assertThrows(IllegalArgumentException.class,
                () -> new RemoteControlWithMultipleUndo(-1));
    }

    @Test
    void onAndOffCommandsAreExecutedAndStoredInUndo() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(3);
        Lamp lamp = new Lamp("Living Room");

        Command on = new LampOnCommand(lamp);
        Command off = new LampOffCommand(lamp);

        remote.setCommand(0, on, off);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);

        String state = remote.toString();

        assertTrue(state.contains(LampOnCommand.class.getName()));
        assertTrue(state.contains(LampOffCommand.class.getName()));
    }

    @Test
    void undoRemovesLastCommandAndMovesItToRedo() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(2);
        Lamp lamp = new Lamp("Bedroom");

        Command on = new LampOnCommand(lamp);
        Command off = new LampOffCommand(lamp);

        remote.setCommand(0, on, off);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);

        remote.undoButtonWasPushed();

        String state = remote.toString();

        assertTrue(state.contains("[redo stack]"));
        assertTrue(state.contains(LampOffCommand.class.getName()));
    }

    @Test
    void redoReexecutesLastUndoneCommand() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(2);
        Lamp lamp = new Lamp("Kitchen");

        Command on = new LampOnCommand(lamp);

        remote.setCommand(0, on, new NoCommand());

        remote.onButtonWasPushed(0);
        remote.undoButtonWasPushed();
        remote.redoButtonWasPushed();

        String state = remote.toString();

        assertTrue(state.contains(LampOnCommand.class.getName()));
        assertTrue(state.contains("[redo stack] []"));
    }

    @Test
    void redoStackIsClearedWhenNewCommandIsExecuted() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(3);
        Lamp lamp = new Lamp("Hall");

        Command on = new LampOnCommand(lamp);
        Command off = new LampOffCommand(lamp);

        remote.setCommand(0, on, off);

        remote.onButtonWasPushed(0);
        remote.undoButtonWasPushed();

        remote.offButtonWasPushed(0);

        String state = remote.toString();

        assertTrue(state.contains("[redo stack] []"));
    }

    @Test
    void undoLimitIsRespectedOldestCommandsAreDiscarded() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(2);
        Lamp lamp = new Lamp("Office");

        Command on = new LampOnCommand(lamp);
        Command off = new LampOffCommand(lamp);

        remote.setCommand(0, on, off);

        remote.onButtonWasPushed(0);
        remote.offButtonWasPushed(0);
        remote.onButtonWasPushed(0);   // 3 -> el primero se descarta

        remote.undoButtonWasPushed();
        remote.undoButtonWasPushed();
        remote.undoButtonWasPushed();

        assertDoesNotThrow(remote::undoButtonWasPushed);
    }

    @Test
    void pressingButtonsWithoutSettingCommandsDoesNotFail() {
        RemoteControlWithMultipleUndo remote = new RemoteControlWithMultipleUndo(1);

        assertDoesNotThrow(() -> remote.onButtonWasPushed(0));
        assertDoesNotThrow(() -> remote.offButtonWasPushed(0));
        assertDoesNotThrow(remote::undoButtonWasPushed);
        assertDoesNotThrow(remote::redoButtonWasPushed);
    }
}
