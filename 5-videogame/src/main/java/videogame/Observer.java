package videogame;
import videogame.character.Character;

public interface Observer {
    public void updateStartingFight();
    public void updateAttack(Character attacker, Character defender, int damage);
    public void updateWinnerAndLoser(Character winner, Character loser);
}
