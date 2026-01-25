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
    Lamp lamp;

    @Test
    void execute_shouldExecuteAllCommands() {
        Command lampOn = new LampOnCommand(lamp);
        Command lampOff = new LampOffCommand(lamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.execute();

        InOrder inOrder = inOrder(lamp);
        inOrder.verify(lamp).on();
        inOrder.verify(lamp).off();
        verifyNoMoreInteractions(lamp);
    }

    @Test
    void undo_shouldUndoAllCommands() {
        Command lampOn = new LampOnCommand(lamp);
        Command lampOff = new LampOffCommand(lamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.undo();

        InOrder inOrder = inOrder(lamp);
        inOrder.verify(lamp).off();
        inOrder.verify(lamp).on();
        verifyNoMoreInteractions(lamp);
    }

    @Test
    void executeAndUndo_shouldCallCorrectMethods() {
        Command lampOn = new LampOnCommand(lamp);
        Command lampOff = new LampOffCommand(lamp);

        MacroCommand macro = new MacroCommand(new Command[]{lampOn, lampOff});

        macro.execute();
        macro.undo();

        verify(lamp, times(2)).on();
        verify(lamp, times(2)).off();
    }
}
