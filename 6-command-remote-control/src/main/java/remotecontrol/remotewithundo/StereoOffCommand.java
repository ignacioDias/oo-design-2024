package remotecontrol.remotewithundo;


public class StereoOffCommand implements Command {
	Stereo stereo;
 	StereoMode currentMode;
	int currentVolume;

	public StereoOffCommand(Stereo stereo) {
		this.stereo = stereo;
	}
 
	public void execute() {
		currentVolume = stereo.currentVolume;
		currentMode = stereo.stereoMode;
		stereo.off();
	}

	@Override
	public void undo() {
		switch(currentMode) {
			case ON -> stereo.on();
			case DVD_INPUT -> stereo.setDVD();
			case CD_INPUT -> stereo.setCD();
			case RADIO -> stereo.setRadio();
			case OFF -> this.execute();
			default -> throw new IllegalStateException("Illegal state");
		}
		stereo.setVolume(currentVolume);
		currentMode = StereoMode.OFF;
		currentVolume = 0;
	}
}
