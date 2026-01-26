package texteditor;

import texteditor.command.Command;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class TextEditor {
    List<Command> commands = new ArrayList<>();
    Deque<Command> undoDeque = new ArrayDeque<>();
    Deque<Command> redoDeque = new ArrayDeque<>();

    public void addCommand(Command command) {
        commands.add(command);
    }

    public List<Command> getCommands() {
        return this.commands;
    }
    public void executeCommand(int slot) {
        Command command = commands.get(slot);
        if(command == null)
            throw new IllegalArgumentException("Illegal slot");
        command.initialize();
        command.execute();

        Command copyCommand = command.duplicate();
        storeCommand(copyCommand);
    }

    private void storeCommand(Command command) {
        undoDeque.push(command);
        redoDeque.clear();
    }
    public void undoButtonWasPushed() {
        if (undoDeque.isEmpty()) return;
        Command command = undoDeque.pop();
        command.undo();
        redoDeque.push(command);
    }

    public void redoButtonWasPushed() {
        if (redoDeque.isEmpty()) return;
        Command command = redoDeque.pop();
        command.execute();
        undoDeque.push(command);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TextEditor State:\n");
        sb.append("================\n");
        sb.append("Available Commands: ").append(commands.size()).append("\n");
        for (int i = 0; i < commands.size(); i++) {
            sb.append("  [").append(i).append("] ").append(commands.get(i).getClass().getSimpleName()).append("\n");
        }
        sb.append("\nUndo Stack: ").append(undoDeque.size()).append(" command(s)\n");
        if (!undoDeque.isEmpty()) {
            int count = 0;
            for (Command cmd : undoDeque) {
                sb.append("  ").append(count++).append(". ").append(cmd.getClass().getSimpleName()).append("\n");
                if (count >= 5) {
                    sb.append("  ... (").append(undoDeque.size() - 5).append(" more)\n");
                    break;
                }
            }
        }
        sb.append("\nRedo Stack: ").append(redoDeque.size()).append(" command(s)\n");
        if (!redoDeque.isEmpty()) {
            int count = 0;
            for (Command cmd : redoDeque) {
                sb.append("  ").append(count++).append(". ").append(cmd.getClass().getSimpleName()).append("\n");
                if (count >= 5) {
                    sb.append("  ... (").append(redoDeque.size() - 5).append(" more)\n");
                    break;
                }
            }
        }
        return sb.toString();
    }
}
