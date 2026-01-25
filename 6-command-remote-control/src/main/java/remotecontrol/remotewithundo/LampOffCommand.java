package remotecontrol.remotewithundo;

public class LampOffCommand implements Command {

    Lamp lamp;

    public LampOffCommand(Lamp lamp) {
        this.lamp = lamp;
    }

    @Override
    public void execute() {
        lamp.off();
    }

    @Override
    public void undo() {
        lamp.on();
    }
}
