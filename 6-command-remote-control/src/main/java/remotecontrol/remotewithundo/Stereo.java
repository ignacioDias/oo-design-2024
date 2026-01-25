package remotecontrol.remotewithundo;

public class Stereo {
	String location;
	public StereoMode stereoMode;
	public int currentVolume = 0;

	public Stereo(String location) {
		this.location = location;
	}

	public void on() {
		this.stereoMode = StereoMode.ON;
		System.out.println(location + " stereo is on");
	}

	public void off() {
		this.stereoMode = StereoMode.OFF;
		System.out.println(location + " stereo is off");
	}

	public void setCD() {
		this.stereoMode = StereoMode.CD_INPUT;
		System.out.println(location + " stereo is set for CD input");
	}

	public void setDVD() {
		this.stereoMode = StereoMode.DVD_INPUT;
		System.out.println(location + " stereo is set for DVD input");
	}

	public void setRadio() {
		this.stereoMode = StereoMode.RADIO;
		System.out.println(location + " stereo is set for Radio");
	}

	public void setVolume(int volume) {
		// code to set the volume
		// valid range: 1-11 (after all 11 is better than 10, right?)
		this.currentVolume = volume;
		System.out.println(location + " stereo volume set to " + volume);
	}
}
