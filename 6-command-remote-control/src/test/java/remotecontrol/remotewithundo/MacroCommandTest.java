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
    Command stereoOn;
    @Mock
    Command stereoRadio;
    @Mock
    Command stereoOff;

    @Test
    void execute_executes_all_commands_in_order() {
        Command[] commands = { stereoOn, stereoRadio, stereoOff };
        MacroCommand macro = new MacroCommand(commands);

        macro.execute();

        InOrder inOrder = inOrder(stereoOn, stereoRadio, stereoOff);
        inOrder.verify(stereoOn).execute();
        inOrder.verify(stereoRadio).execute();
        inOrder.verify(stereoOff).execute();
    }

    @Test
    void undo_calls_undo_on_all_commands_in_order() {
        Command[] commands = { stereoOn, stereoRadio, stereoOff };
        MacroCommand macro = new MacroCommand(commands);

        macro.undo();

        InOrder inOrder = inOrder(stereoOn, stereoRadio, stereoOff);
        inOrder.verify(stereoOn).undo();
        inOrder.verify(stereoRadio).undo();
        inOrder.verify(stereoOff).undo();
    }

    @Test
    void execute_and_undo_both_delegate_correctly() {
        Command[] commands = { stereoOn, stereoRadio };
        MacroCommand macro = new MacroCommand(commands);

        macro.execute();
        macro.undo();

        verify(stereoOn).execute();
        verify(stereoRadio).execute();
        verify(stereoOn).undo();
        verify(stereoRadio).undo();
    }
}
