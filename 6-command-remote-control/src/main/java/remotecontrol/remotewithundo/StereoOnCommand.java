package remotecontrol.remotewithundo;

public class StereoOnCommand implements Command {
	Stereo stereo;
 
	public StereoOnCommand(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		if(stereo.stereoMode.equals(StereoMode.OFF))
			stereo.on();
	}

	@Override
	public void undo() {
		if(stereo.stereoMode.equals(StereoMode.ON))
			stereo.off();
	}
}
