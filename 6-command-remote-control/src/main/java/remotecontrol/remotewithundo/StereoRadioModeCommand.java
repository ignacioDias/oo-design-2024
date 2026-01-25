package remotecontrol.remotewithundo;

public class StereoRadioModeCommand implements Command {
    private Stereo stereo;
    private StereoMode currentMode;

    public StereoRadioModeCommand(Stereo stereo) {
        this.stereo = stereo;
    }
    @Override
    public void execute() {
        if(stereo.stereoMode.equals(StereoMode.OFF))
            throw new IllegalStateException("Radio OFF");
        currentMode = stereo.stereoMode;
        stereo.setRadio();
    }

    @Override
    public void undo() {
        switch(currentMode) {
            case ON -> stereo.on();
            case DVD_INPUT -> stereo.setDVD();
            case CD_INPUT -> stereo.setCD();
            case RADIO -> this.execute();
            case OFF -> stereo.off();
            default -> throw new IllegalStateException("Illegal state");
        }
        currentMode = StereoMode.RADIO;
    }
}
