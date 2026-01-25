package remotecontrol.remotewithundo;

public class LampOnCommand implements Command {

    Lamp lamp;

    public LampOnCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        lamp.on();
    }

    @Override
    public void undo() {
        lamp.off();
    }
}
