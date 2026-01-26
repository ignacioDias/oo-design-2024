package texteditor.command;

public interface Command {
    public void execute();
    public void undo();
    public void initialize();
    public Command duplicate();
}
