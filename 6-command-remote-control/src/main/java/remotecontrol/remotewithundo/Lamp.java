package remotecontrol.remotewithundo;

public class Lamp {
    String location;

    public Lamp(String location) {
        this.location = location;
    }
    public void on() {
        System.out.println(location + " Lamp is on");
    }
    public void off() {
        System.out.println(location + " Lamp is off");
    }
}
