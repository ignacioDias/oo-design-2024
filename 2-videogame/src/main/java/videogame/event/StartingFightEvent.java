package videogame.event;

public class StartingFightEvent implements FightEvent {
    public StartingFightEvent() {
    }
    @Override
    public String generateDefaultOutput() {
        return "Fight starts";
    }
}
