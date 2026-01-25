package remotecontrol.remotewithundo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MacroCommandTest {

    @Mock
    Lamp livingRoomLamp;

    @Test
    void execute_shouldExecuteAllCommands() {
        Command lampOn = new LampOnCommand(livingRoomLamp);
        Command lampOff = new LampOffCommand(livingRoomLamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.execute();

        InOrder inOrder = inOrder(livingRoomLamp);
        inOrder.verify(livingRoomLamp).on();
        inOrder.verify(livingRoomLamp).off();
        verifyNoMoreInteractions(livingRoomLamp);
    }

    @Test
    void undo_shouldUndoAllCommands() {
        Command lampOn = new LampOnCommand(livingRoomLamp);
        Command lampOff = new LampOffCommand(livingRoomLamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.undo();

        InOrder inOrder = inOrder(livingRoomLamp);
        inOrder.verify(livingRoomLamp).off();
        inOrder.verify(livingRoomLamp).on();
        verifyNoMoreInteractions(livingRoomLamp);
    }

    @Test
    void executeAndUndo_shouldCallCorrectMethods() {
        Command lampOn = new LampOnCommand(livingRoomLamp);
        Command lampOff = new LampOffCommand(livingRoomLamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.execute();
        macro.undo();

        verify(livingRoomLamp, times(2)).on();
        verify(livingRoomLamp, times(2)).off();
    }
}
