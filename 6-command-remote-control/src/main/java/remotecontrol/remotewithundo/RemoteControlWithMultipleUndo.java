package remotecontrol.remotewithundo;


import java.util.Deque;
import java.util.ArrayDeque;

public class RemoteControlWithMultipleUndo {
    Command[] onCommands;
    Command[] offCommands;
    Deque<Command> undoDeque;
    Deque<Command> redoDeque;

    int limit;

    public RemoteControlWithMultipleUndo(int limit) {
        if (limit <= 0) {
            throw new IllegalArgumentException("invalid limit");
        }
        onCommands = new Command[7];
        offCommands = new Command[7];
        undoDeque = new ArrayDeque<>();
        redoDeque = new ArrayDeque<>();
        this.limit = limit;

        Command noCommand = new NoCommand();
        for(int i = 0; i < 7; i++) {
            onCommands[i] = noCommand;
            offCommands[i] = noCommand;
        }
    }

    public void setCommand(int slot, Command onCommand, Command offCommand) {
        onCommands[slot] = onCommand;
        offCommands[slot] = offCommand;
    }

    public void onButtonWasPushed(int slot) {
        executeAndStore(onCommands[slot]);
    }

    public void offButtonWasPushed(int slot) {
        executeAndStore(offCommands[slot]);
    }

    private void executeAndStore(Command command) {
        command.execute();
        pushWithLimit(undoDeque, command);
        redoDeque.clear();
    }
    public void undoButtonWasPushed() {
        if (undoDeque.isEmpty()) return;

        Command command = undoDeque.pop();
        command.undo();
        pushWithLimit(redoDeque, command);
    }

    public void redoButtonWasPushed() {
        if (redoDeque.isEmpty()) return;

        Command command = redoDeque.pop();
        command.execute();
        pushWithLimit(undoDeque, command);
    }

    private void pushWithLimit(Deque<Command> deque, Command command) {
        if (deque.size() == limit) {
            deque.removeLast();
        }
        deque.push(command);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n------ Remote Control -------\n");
        for (int i = 0; i < onCommands.length; i++) {
            sb.append("[slot ").append(i).append("] ")
                    .append(onCommands[i].getClass().getName())
                    .append("    ")
                    .append(offCommands[i].getClass().getName())
                    .append("\n");
        }
        sb.append("[undo stack] ").append(undoDeque).append("\n");
        sb.append("[redo stack] ").append(redoDeque).append("\n");
        return sb.toString();
    }
}